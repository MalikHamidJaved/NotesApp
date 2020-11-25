package com.example.noteapp.database

import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.noteapp.App
import com.example.noteapp.database.dao.NotesDao
import com.example.noteapp.database.model.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

/**
 * The NotesDaoTest.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
@RunWith(AndroidJUnit4::class)
class NotesDaoTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var notesDao: NotesDao

    @Before
    fun initDB() {
        val context = ApplicationProvider.getApplicationContext<App>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        notesDao = appDatabase.getNotesDao()
    }

    @Test
    fun setNote() {
        runBlocking {
            val inserted = notesDao.setNote(
                Notes(
                    1,
                    "Title 1",
                    "Lorem ipsum is a dummy text",
                    ""
                )
            ) > 0
            assertTrue(inserted)
        }
    }

    @Test
    fun updateNote() {
        runBlocking {
            val oldTitle = "Title 1"
            val newTitle = "Title 2"
            val note = Notes(1, oldTitle, "Lorem ipsum is a dummy text", "")
            val inserted = notesDao.setNote(note) > 0
            var updated = false
            if (inserted)
                updated = notesDao.updateNote(note.also { it.title = newTitle }) > 0

            assertTrue(updated)
        }
    }

    @Test
    fun deleteNotes() {
        var observer: Observer<List<Notes>>? = null
        notesDao.setNote(Notes(1, "Title 1", "Lorem ipsum is a dummy text", ""))
        runBlocking(Dispatchers.Main.immediate) {
            val notes = notesDao.getNotes()
            observer = Observer<List<Notes>> {
                notes.removeObserver(observer!!)
                assertTrue(it.isEmpty())
            }
            notes.observeForever(observer!!)
        }
        notesDao.deleteAll()
    }

    @Test
    fun deleteSingleNote() {
        notesDao.setNote(Notes(1, "Title 1", "Lorem ipsum is a dummy text", ""))
        notesDao.deleteById(1)
        runBlocking(Dispatchers.Main.immediate) {
            val note = notesDao.getSingleNote(1)
            assertTrue(note == null)
        }
    }

    @Test
    fun getNotes() {
        var observer: Observer<List<Notes>>? = null
        runBlocking(Dispatchers.Main.immediate) {
            val notes = notesDao.getNotes()
            observer = Observer<List<Notes>> {
                notes.removeObserver(observer!!)
                assertTrue(it.isNotEmpty())
            }
            notes.observeForever(observer!!)
        }
        notesDao.setNote(Notes(1, "Title 1", "Lorem ipsum is a dummy text", ""))
    }

    @Test
    fun getSingleNote() {
        notesDao.setNote(Notes(1, "Title 1", "Lorem ipsum is a dummy text", ""))
        runBlocking(Dispatchers.Main.immediate) {
            val note = notesDao.getSingleNote(1)
            assertTrue(note!!.id == 1)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        appDatabase.close()
    }
}