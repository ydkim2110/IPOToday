package com.ipotoday.ipotoday.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(context: Context) {



    private val prefs: SharedPreferences = PreferenceHelper.defaultPrefs(context)

    fun getPrefs(): SharedPreferences = prefs

    fun getFirstTime() =
        prefs.getBoolean(FIRST_LOGIN, true)

    fun setFirstTime(value: Boolean) {
        prefs.edit { putBoolean(FIRST_LOGIN, value) }
    }

}