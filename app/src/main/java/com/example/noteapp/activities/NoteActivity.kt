package com.example.noteapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.noteapp.R
import com.example.noteapp.extensions.showToastMsg
import com.example.noteapp.viewmodels.NoteViewModel
import kotlinx.android.synthetic.main.activity_note.*

class NoteActivity : AppCompatActivity(), NoteViewModel.View, View.OnClickListener {

    private val noteViewModel: NoteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        noteViewModel.attachView(this)
        btnSave.setOnClickListener(this)
    }

    override fun onUpdateUser(error: String) {
        showToastMsg(error)
    }

    override fun onSuccess(message: String) {
        showToastMsg(message)
        finish()
    }

    override fun updateButtonState(active : Boolean){
        btnSave.isEnabled = active
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSave -> {
                noteViewModel.saveNoteInDB(etTitle.text.toString(), etImg.text.toString(), etDetails.text.toString())
            }
        }
    }
}