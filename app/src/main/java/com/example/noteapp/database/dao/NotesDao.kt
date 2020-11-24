package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapp.database.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setMessage(notes: Notes)

    @Query("SELECT * from message_table ORDER BY id ASC")
    fun getMessages() : LiveData<List<Notes>>

    @Query("DELETE FROM message_table")
    fun deleteAll()
}