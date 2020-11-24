package com.example.noteapp

import android.app.Application
import android.content.Context
import com.example.noteapp.di.DIFramework

/**
 * The App.kt, Application class
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DIFramework.init(this)
    }

    companion object {
        var instance: App? = null
        fun getAppContext(): Context {
            return instance as Context
        }
    }
}