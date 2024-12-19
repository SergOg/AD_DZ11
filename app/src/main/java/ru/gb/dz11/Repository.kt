package ru.gb.dz11

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

private const val PREFERENCE_NAME = "prefs_name"
private const val SHARED_PREFS_KEY = "prefs_key"

class Repository {
    private lateinit var prefs: SharedPreferences

    fun getText(context: Context): String {
        return when {
            getDataFromLocalVariable() != null -> getDataFromLocalVariable()!!
            getDataFromSharedPreference(context) != null -> getDataFromSharedPreference(context)!!
            else -> " "
        }
    }

    private fun getDataFromSharedPreference(context: Context): String? {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return prefs.getString(SHARED_PREFS_KEY, null)
    }

    private fun getDataFromLocalVariable(): String? {
        return localValue
    }

    fun saveText(text: String, context: Context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(SHARED_PREFS_KEY, text)
        editor.apply()
        localValue = text
    }

    @SuppressLint("CommitPrefEdits")
    fun clearText(context: Context) {
        prefs = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.remove(SHARED_PREFS_KEY)
        editor.apply()
        localValue = null
    }

    companion object {
        var localValue: String? = ""
    }
}