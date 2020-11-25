package com.example.noteapp.activities

import android.os.Bundle
import com.example.noteapp.R
import com.example.noteapp.base.BaseActivity
import com.example.noteapp.extensions.replaceFragmentSafely
import com.example.noteapp.fragments.HomeFragment

/**
 * The MainActivity.kt, Main activity class, launcher activity
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragmentSafely(HomeFragment())
    }
}