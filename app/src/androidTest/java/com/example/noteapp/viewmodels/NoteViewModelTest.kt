    package com.example.noteapp.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.noteapp.database.model.Notes
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class NoteViewModelTest : HomeViewModel.View {

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Before
    fun initViewModel() {
        homeViewModel = HomeViewModel().apply {
            attachView(this@NoteViewModelTest)
            attachObserver(lifecycleOwner)
        }
    }

    @Test
    fun getNote() {
        homeViewModel.getNotesFromDB()
    }

    override fun onNotesFromDB(notes: List<Notes>) {
        assertTrue(notes.isNotEmpty())
    }

    override fun onUpdateUser(message: String) {
        assertTrue(message.isNotEmpty())
    }

    override fun showProgressBar() {

    }

    override fun dismissProgressBar() {

    }
}