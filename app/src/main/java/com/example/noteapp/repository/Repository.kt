package com.example.noteapp.repository

/**
 * The Repository.kt
 *
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class Repository private constructor() {


    companion object {
        private var instance: Repository? = null
        fun getInstance(): Repository {
            if (instance == null)
                instance =
                    Repository()
            return instance!!
        }
    }
}