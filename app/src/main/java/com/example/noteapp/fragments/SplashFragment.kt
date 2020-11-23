package com.example.noteapp.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noteapp.R
import com.example.noteapp.extensions.replaceFragment

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class SplashFragment : Fragment() {

    companion object {
        const val SPLASH_DURATION = 2000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            replaceFragment(HomeFragment(), addToBackStack = false)
        }, SPLASH_DURATION)
    }
}
