package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.database.model.Notes

/**
 * The NotesDao.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setNote(note: Notes)

    @Query("SELECT * from note_table ORDER BY id ASC")
    fun getNotes(): LiveData<List<Notes>>

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table WHERE id=:id ")
    fun getSingleNote(id: Int): LiveData<Notes>

    @Update
    fun updateNote(vararg note: Notes)

    @Query("DELETE FROM note_table WHERE id = :id")
    fun deleteById(id: Int)
}