package com.example.noteapp.viewmodels

import com.example.noteapp.base.BaseViewModel
import com.example.noteapp.database.model.Notes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


/**
 * The HomeViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NoteViewModel : BaseViewModel<NoteViewModel.View>() {
    //get the single note from DB using Async, new API in kotlin Coroutines
    @ExperimentalCoroutinesApi
    fun getSingleNote(id: Int) {
        val getDataAsAsync = GlobalScope.async { notesRepository.getNote(id) }
        getDataAsAsync.invokeOnCompletion { cause ->
            if (cause == null) {
                getDataAsAsync.getCompleted()?.let {
                    getView().onSingleNote(it)
                }
            }
        }
    }


    //Save note in DB using
    fun saveNoteInDB(title: String?, imgUrl: String?= null, description: String?) {
        validateData(title, description).let {
            if (it) {
                notesRepository.saveNote(
                    Notes(
                        title = title!!,
                        image = imgUrl,
                        description = description!!
                    )
                )
                getView().onSuccess("saved successfully!")
                getView().updateButtonState(true)
            } else
                return
        }
    }

    //To update the note in DB sync way
    fun updateNoteInDB(id: Int, title: String?, imgUrl: String?, description: String?) {
        validateData(title, description).let {
            if (it) {
                notesRepository.updateNote(
                    Notes(
                        id = id,
                        title = title!!,
                        image = imgUrl,
                        description = description!!,
                        isUpdated = true
                    )
                )
                getView().onSuccess("updated successfully!")
                getView().updateButtonState(true)
            } else
                return
        }
    }

    //Validation method to validate data
    private fun validateData(title: String?, description: String?): Boolean {
        getView().updateButtonState(false)

        if (title.isNullOrBlank()) {
            getView().onUpdateUser("Title cannot be empty")
            getView().updateButtonState(true)
            return false
        }

        if (description.isNullOrBlank()) {
            getView().onUpdateUser("Description cannot be empty")
            getView().updateButtonState(true)
            return false
        }
        return true
    }

    //Delete the  note in DB in sync way
    fun deleteItem(id: Int) {
        getView().updateButtonState(false)
        notesRepository.deleteById(id)
        getView().onSuccess("Deleted successfully")
    }

    interface View {
        fun onUpdateUser(error: String)
        fun onSingleNote(notes: Notes)
        fun onSuccess(message: String)
        fun updateButtonState(active: Boolean)
    }
}