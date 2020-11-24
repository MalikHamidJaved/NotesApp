package com.example.noteapp.repository

import com.example.noteapp.base.BaseRepository
import com.example.noteapp.database.AppDatabase
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
 */

class NotesRepository private constructor() : BaseRepository(),  CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var notesDao: NotesDao? = appDatabase.getNotesDao()

    fun getNotesList() = notesDao?.getMessages()

    fun saveNote(message: Notes) {
        launch { saveNoteInDB(message) }
    }

    private suspend fun saveNoteInDB(message: Notes) {
        withContext(Dispatchers.IO) {
            notesDao?.setMessage(message)
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