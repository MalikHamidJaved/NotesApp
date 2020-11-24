package com.example.noteapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "message_table")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "isUpdated")
    var isUpdated: Boolean = false,

)