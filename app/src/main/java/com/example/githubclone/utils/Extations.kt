package com.example.githubclone.utils

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubclone.App

fun shPreferencePutString(key: String, value: String) {
    val shPref = App.instance.getSharedPreferences("shPref", Context.MODE_PRIVATE)
    shPref.edit().putString(key, value).apply()
}

fun shPreferenceGetString(key: String, defValue: String): String {
    val shPref = App.instance.getSharedPreferences("shPref", Context.MODE_PRIVATE)
    return shPref.getString(key, defValue).toString()
}

fun shPreferencePutBoolean(key: String, value: Boolean) {
    val shPref = App.instance.getSharedPreferences("shPref", Context.MODE_PRIVATE)
    shPref.edit().putBoolean(key, value).apply()
}

fun shPreferenceGetBoolean(key: String, defValue: Boolean): Boolean {
    val shPref = App.instance.getSharedPreferences("shPref", Context.MODE_PRIVATE)
    return shPref.getBoolean(key, defValue)
}

