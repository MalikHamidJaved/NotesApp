package com.example.noteapp.viewmodels

import com.example.noteapp.base.BaseViewModel
import com.example.noteapp.database.model.Notes
import com.example.noteapp.extensions.checkValidURL

/**
 * The HomeViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NoteViewModel : BaseViewModel<NoteViewModel.View>() {

    fun getSingleNote(id: Int) {
        notesRepository.getNote(id)?.observe(getObserver(), {
            if (it != null) {
                getView().onSingleNote(it)
            }
        })
    }

    fun saveNoteInDB(title: String?, imgUrl: String?, description: String?) {
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