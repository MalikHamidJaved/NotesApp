package com.example.noteapp.utils

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * The DialogUtil.kt to handle the custom dialogues
 * @author Malik Dawar, malikdawar@hotmail.com
 */

class DialogUtils {
    companion object {

        fun showProgressDialog(context: Context, message: String? = null, cancelable: Boolean = false): KProgressHUD {
            return KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(message)
                .setCancellable(cancelable)
                .setMaxProgress(100) as KProgressHUD
        }
    }
}