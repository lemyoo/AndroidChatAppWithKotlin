package com.example.andriodkotlinchat.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences {
    private val fileName: String ="My_SHARED_FILENAME"

    fun removeAccessToken(context: Context) {
        val preference:SharedPreferences =context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        preference.edit().remove("accessToken").apply()
    }
    fun getAccessToken(context: Context): String{
        val preference:SharedPreferences =context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        return preference.getString("accessToken","").toString()
    }
    fun setAccessToken(context: Context,accessToken: String){
        val preferences:SharedPreferences =context.getSharedPreferences(fileName,Context.MODE_PRIVATE)
        preferences.edit().putString("accessToken",accessToken).apply()

    }
}