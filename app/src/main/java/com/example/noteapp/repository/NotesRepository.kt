package com.example.noteapp.repository

import com.example.noteapp.base.BaseRepository
import com.example.noteapp.database.dao.NotesDao
import com.example.noteapp.database.model.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * The Repository.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 * Access point on the DB
 */

class NotesRepository private constructor() : BaseRepository(),  CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var notesDao: NotesDao? = appDatabase.getNotesDao()

    fun getNotesList() = notesDao?.getNotes()

    suspend fun getNote(id: Int) = notesDao?.getSingleNote(id)

    fun saveNote(note: Notes) {
        launch { saveNoteInDB(note) }
    }

    private suspend fun saveNoteInDB(note: Notes) {
        withContext(Dispatchers.IO) {
            notesDao?.setNote(note)
        }
    }

    fun updateNote(note: Notes) {
        launch { updateNoteInDB(note) }
    }

    private suspend fun updateNoteInDB(note: Notes) {
        withContext(Dispatchers.IO) {
            notesDao?.updateNote(note)
        }
    }

    fun deleteById(id : Int){
        launch { deleteNote(id) }
    }

    private suspend fun deleteNote(id : Int) {
        withContext(Dispatchers.IO) {
            notesDao?.deleteById(id)
        }
    }


    companion object {
        private var instance: NotesRepository? = null
        fun getInstance(): NotesRepository {
            if (instance == null)
                instance =
                    NotesRepository()
            return instance!!
        }
    }
}