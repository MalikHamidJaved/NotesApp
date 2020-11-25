package com.example.noteapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.activities.NoteActivity
import com.example.noteapp.adapter.NotesAdapter
import com.example.noteapp.base.BaseFragment
import com.example.noteapp.database.model.Notes
import com.example.noteapp.extensions.showToastMsg
import com.example.noteapp.utils.Const.NOTE_ID
import com.example.noteapp.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class HomeFragment : BaseFragment(), HomeViewModel.View, NotesAdapter.NoteItemClickListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private var notesAdapter: NotesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.let {
            it.attachObserver(this)
            it.attachView(this)
            it.getNotesFromDB()
        }

        faBtnCreate.setOnClickListener {
            startActivity(Intent(mainActivity, NoteActivity::class.java))
        }
    }

    //once we get the data from repo, populate it with the help of the adapter, NotesAdapter()
    override fun onNotesFromDB(notes: List<Notes>) {
        notesAdapter = NotesAdapter(this, notes)
        notesAdapter.let {
            notesRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                itemAnimator = DefaultItemAnimator()
                adapter = it
            }
            it?.notifyDataSetChanged()
        }
    }

    //To update the user against any unusual situation
    override fun onUpdateUser(message: String) {
        showToastMsg(message)
    }

    //Show the progress while fetching date from repo
    override fun showProgressBar() {
        progressDialog.show()
    }

    //Hide the progress after fetching date from repo
    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }

    //On note item click listener
    override fun onItemClickListener(note: Notes) {
        startActivity(Intent(mainActivity, NoteActivity::class.java).putExtra(NOTE_ID, note.id))
    }
}
