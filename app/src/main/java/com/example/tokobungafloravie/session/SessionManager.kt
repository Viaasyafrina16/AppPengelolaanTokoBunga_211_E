package com.example.tokobungafloravie.session

import android.content.Context
object SessionManager {

    private const val PREF_NAME = "floravie_session"
    private const val KEY_IS_LOGIN = "is_login"

    fun setLogin(context: Context, isLogin: Boolean) {
        val sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sp.edit().putBoolean(KEY_IS_LOGIN, isLogin).apply()
    }

    fun isLogin(context: Context): Boolean {
        val sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sp.getBoolean(KEY_IS_LOGIN, false)
    }

    fun clear(context: Context) {
        val sp = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sp.edit().clear().apply()
    }
}