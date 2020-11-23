package com.example.noteapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.noteapp.R
import com.example.noteapp.base.BaseFragment
import com.example.noteapp.viewmodels.HomeViewModel

/**
 * The HomeFragment.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class HomeFragment : BaseFragment(),  HomeViewModel.View{

    private val homeViewModel : HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.let {
            it.attachObserver(this)
            it.attachView(this)
        }

    }

    override fun showProgressBar() {

    }

    override fun dismissProgressBar() {

    }
}
