package com.example.noteapp.base

import com.example.noteapp.database.AppDatabase
import org.koin.java.KoinJavaComponent

abstract class BaseRepository{
    protected val appDatabase: AppDatabase by KoinJavaComponent.inject(AppDatabase::class.java)
}
