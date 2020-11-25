package com.example.noteapp.base

import com.example.noteapp.database.AppDatabase
import org.koin.java.KoinJavaComponent.inject

/**
 * The BaseRepository.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
abstract class BaseRepository{
    //injecting the DB object to the repository
    protected val appDatabase: AppDatabase by inject(AppDatabase::class.java)
}
