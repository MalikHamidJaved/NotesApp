package com.example.noteapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * The Notes.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

@Entity(tableName = "note_table")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "date")
    var date: Long? = System.currentTimeMillis(),

    @ColumnInfo(name = "isUpdated")
    var isUpdated: Boolean = false
)