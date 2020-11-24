package com.example.noteapp.utils

import android.widget.ImageView
import com.example.noteapp.R
import io.pixel.android.Pixel
import io.pixel.android.config.PixelOptions

/**
 * A convenient function to load images from internet on ImageView.
 * @see Pixel The library used which loads memory cached images.
 */
fun ImageView.load(url: String?) {
    Pixel.load(
        path = url,
        imageView = this,
        pixelOptions = PixelOptions.Builder()
            .setPlaceholderResource(R.mipmap.ic_launcher).build()
    )
}