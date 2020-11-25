package com.example.noteapp.di

import android.content.Context
import com.example.noteapp.database.AppDatabase
import com.example.noteapp.repository.NotesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * DIFramework.kt
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 * @author Malik Dawar
 */
object DIFramework {

    fun init(context: Context) {
        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            val repoModule = module {
                single { AppDatabase.getDatabase(androidContext()) }
                single { NotesRepository.getInstance() }
            }
            // declare modules
            modules(repoModule)
        }
    }
}