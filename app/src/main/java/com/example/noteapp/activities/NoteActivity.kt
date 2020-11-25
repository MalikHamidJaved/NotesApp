package com.example.noteapp.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.noteapp.R
import com.example.noteapp.base.BaseActivity
import com.example.noteapp.database.model.Notes
import com.example.noteapp.extensions.showToastMsg
import com.example.noteapp.extensions.visible
import com.example.noteapp.utils.Const.NOTE_ID
import com.example.noteapp.utils.load
import com.example.noteapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * The NoteActivity.kt, to crete/edit/delete
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NoteActivity : BaseActivity(), NoteViewModel.View, View.OnClickListener {

    private val noteViewModel: NoteViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        //attach view and observer
        noteViewModel.let {
            it.attachView(this)
            it.attachObserver(this)
        }

        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)

        //if intent isn't null, call the viewModel to fetch the note by Id
        intent.extras?.run {
            noteViewModel.getSingleNote(this.getInt(NOTE_ID))
        }
    }

    //update user on each edge case
    override fun onUpdateUser(error: String) {
        showToastMsg(error)
    }

    //on fetching the data against the id if intent isn't null
    private var noteId: Int = 0
    override fun onSingleNote(notes: Notes) {
        runOnUiThread {
            notes.apply {
                btnDelete?.visible()
                noteId = notes.id
                etTitle.setText(title)
                etImg.setText(image)
                etDetails.setText(description)
                imgView.load(image)
            }
        }
    }

    //this method will invoke on success call against update and save
    override fun onSuccess(message: String) {
        showToastMsg(message)
        finish()
    }

    //handling the button to avoid the double click
    override fun updateButtonState(active: Boolean) {
        btnSave.isEnabled = active
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            //handling Onclick button press
            //if intent is null, save new note
            // if intent isn't null, update note
            R.id.btnSave -> {
                intent.extras?.run {
                    noteViewModel.updateNoteInDB(
                        noteId,
                        etTitle.text.toString(),
                        etImg.text.toString(),
                        etDetails.text.toString()
                    )
                } ?: run {
                    noteViewModel.saveNoteInDB(
                        etTitle.text.toString(),
                        etImg.text.toString(),
                        etDetails.text.toString()
                    )
                }
            }
            //to delete the specific note from DB
            R.id.btnDelete -> {
                noteViewModel.deleteItem(noteId)
            }
        }
    }
}