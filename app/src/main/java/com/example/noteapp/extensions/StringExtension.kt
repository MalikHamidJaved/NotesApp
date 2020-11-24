package com.example.noteapp.extensions

import android.annotation.SuppressLint
import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.*

/**
 * The StringExtension.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

/**
 * Extension function to convert timestamp to date
 * @author Dawar Malik.
 */
@SuppressLint("SimpleDateFormat")
fun Long.convertDateToFormat(): String{
    return SimpleDateFormat("MM/dd/yyyy").format( Date(this))
}

/**
 * Extension function to Verify the URL
 * @author Dawar Malik.
 */
fun String.checkValidURL(): String?{
    if(Patterns.WEB_URL.matcher(this).matches())
        return this

    return null
}