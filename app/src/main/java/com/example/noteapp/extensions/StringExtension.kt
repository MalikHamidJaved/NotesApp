package com.example.noteapp.extensions

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function to show toast message
 * @author Dawar Malik.
 */
@SuppressLint("SimpleDateFormat")
fun Long.convertDateToFormat(): String{
    return SimpleDateFormat("MM/dd/yyyy").format( Date(this))
}