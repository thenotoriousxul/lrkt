package com.example.loginrf.data

import android.content.Context


class SessionManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "access_token"
    }

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_TOKEN)
        editor.apply()
    }
}