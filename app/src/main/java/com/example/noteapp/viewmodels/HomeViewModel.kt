package com.example.noteapp.viewmodels

import com.example.noteapp.base.BaseViewModel


/**
 * The HomeViewModel.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class HomeViewModel : BaseViewModel<HomeViewModel.View>() {


    interface View {
        fun showProgressBar()
        fun dismissProgressBar()
    }
}