package com.example.noteapp.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.noteapp.MainActivity

/**
 * The BaseFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
    }
}