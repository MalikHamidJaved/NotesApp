package com.example.noteapp.viewmodels

import com.example.noteapp.base.BaseViewModel
import com.example.noteapp.database.model.Notes

/**
 * The HomeViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class NoteViewModel : BaseViewModel<NoteViewModel.View>() {

    fun saveNoteInDB(title: String?, imgUrl: String?, description: String?) {
        getView().updateButtonState(false)
        if (title.isNullOrBlank()) {
            getView().onUpdateUser("Title cannot be empty")
            getView().updateButtonState(true)
            return
        }

        if (description.isNullOrBlank()) {
            getView().onUpdateUser("Description cannot be empty")
            getView().updateButtonState(true)
            return
        }

        notesRepository.saveNote(Notes(title = title, image = imgUrl, description = description))
        getView().onSuccess("saved successfully!")
        getView().updateButtonState(true)
    }

    interface View {
        fun onUpdateUser(error: String)
        fun onSuccess(message: String)
        fun updateButtonState(active : Boolean)
    }
}