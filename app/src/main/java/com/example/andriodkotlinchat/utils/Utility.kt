package com.example.andriodkotlinchat.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object Utility {
    val apiUrl: String = "https://8e75-102-176-65-17.eu.ngrok.io"

    fun showAlert(context: Context, title: String="", message: String="",onYes: Runnable?=null,onNo: Runnable? = null){
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton(android.R.string.yes){ dialog,which->
            onYes?.run()
        }
        alertDialogBuilder.setNegativeButton(android.R.string.no){dialog, which->
            onNo?.run()
        }
        alertDialogBuilder.show()
    }
}