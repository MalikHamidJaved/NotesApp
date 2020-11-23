package com.example.noteapp.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.noteapp.App

/**
 * Extension function to show toast message
 * @author Dawar Malik.
 */
fun Any.showToastMsg(message: String) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * An Extension to make view Visible
 * @author Dawar Malik.
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 * @author Dawar Malik.
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to close keyboard.
 * @author Dawar Malik.
 */
fun View.closeKeyboard() {
    val inputMethodManager =
        this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}