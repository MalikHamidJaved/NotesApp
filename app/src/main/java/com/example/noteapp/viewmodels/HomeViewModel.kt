package com.example.noteapp.viewmodels

import com.example.noteapp.base.BaseViewModel
import com.example.noteapp.database.model.Notes


/**
 * The HomeViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class HomeViewModel : BaseViewModel<HomeViewModel.View>() {

    fun getNotesFromDB(){
        getView().showProgressBar()
        notesRepository.getNotesList()?.observe(getLifecycleOwner(), {
            getView().dismissProgressBar()
            if (it.isNotEmpty()){
                getView().onNotesFromDB(it)
            }else{
                getView().onUpdateUser("No note found, create new!")
            }
        })
    }

    interface View {
        fun onNotesFromDB(notes : List<Notes>)
        fun onUpdateUser(message : String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}