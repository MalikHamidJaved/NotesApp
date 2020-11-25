package com.example.noteapp.viewmodels

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.noteapp.database.model.Notes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The NoteViewModelTest.kt, to validate ViewModel
 * @author Malik Dawar, malikdawar@hotmail.com
 */
@RunWith(AndroidJUnit4::class)
class NoteViewModelTest : NoteViewModel.View {

    private lateinit var noteViewModel: NoteViewModel

    @Before
    fun initViewModel() {
        noteViewModel = NoteViewModel().apply {
            attachView(this@NoteViewModelTest)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getNoteById() {
        noteViewModel.getSingleNote(1)
    }

    @Test
    fun saveNoteInDbWithOutImage() {
        noteViewModel.saveNoteInDB(title = "hello", description = "this is test data")
    }

    @Test
    fun saveNoteInDbWithImage() {
        noteViewModel.saveNoteInDB(
            title = "hello", description = "this is test data",
            imgUrl = "https://image.shutterstock.com/image-photo/istanbul-bosphorus-bridge-turkey-260nw-691092586.jpg"
        )
    }

    @Test
    fun deleteNoteFromDbById() {
        noteViewModel.deleteItem(1)
    }

    @Test
    fun updateNoteInDB() {
        noteViewModel.updateNoteInDB(
            1,
            "hello",
            "htpps://www.google.com/images/logo.png",
            "hello from gooogle"
        )
    }


    override fun onUpdateUser(error: String) {

    }

    override fun onSingleNote(notes: Notes) {

    }

    override fun onSuccess(message: String) {

    }

    override fun updateButtonState(active: Boolean) {

    }
}