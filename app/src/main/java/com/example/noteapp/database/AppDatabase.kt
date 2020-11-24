package com.example.noteapp.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.database.dao.NotesDao
import com.example.noteapp.database.model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNotesDoa(): NotesDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java, "message_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}