package com.example.noteapp.base

import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.extensions.gone
import com.example.noteapp.extensions.visible
import com.example.noteapp.utils.InternetMonitor
import kotlinx.android.synthetic.main.ui_internet_monitor.*

/**
 * The BaseActivity.kt
 * @author Malik Dawar, malikdawar@hotmail.com
 */

abstract class BaseActivity : AppCompatActivity() {

    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            runOnUiThread {
                top_connection_shower.gone()
            }
        }

        override fun onInternetLost() {
            runOnUiThread {
                top_connection_shower.visible()
            }
        }
    })

    override fun onResume() {
        super.onResume()
        internetMonitor.register(this)
    }

    override fun onPause() {
        super.onPause()
        internetMonitor.unregister(this)
    }
}