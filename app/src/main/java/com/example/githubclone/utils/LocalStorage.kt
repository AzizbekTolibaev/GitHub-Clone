package com.example.githubclone.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.githubclone.App

class LocalStorage {

    companion object {
        val pref: SharedPreferences = App.instance.getSharedPreferences("shPref", Context.MODE_PRIVATE)
    }
}
