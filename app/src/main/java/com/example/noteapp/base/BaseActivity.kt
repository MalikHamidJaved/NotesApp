package com.example.noteapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noteapp.extensions.gone
import com.example.noteapp.extensions.visible
import com.example.noteapp.utils.InternetMonitor
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

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