package com.example.noteapp

import com.example.noteapp.extensions.convertDateToFormat
import org.junit.Test

import org.junit.Assert.*

/**
 * The NotesUnitTest.kt, to helper functions
 * @author Malik Dawar, malikdawar@hotmail.com
 */
class NotesUnitTest {

    @Test
    fun validateDateFormat(){
        assertFalse(System.currentTimeMillis().convertDateToFormat().isBlank())
    }
}

