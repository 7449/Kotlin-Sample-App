package com.common.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * by y on 2017/5/18
 */

object SPUtils {

    private const val SHAREDPREFERENCES_NAME = "kotlin.app"
    private lateinit var sharedPreferences: SharedPreferences

    private fun initSharePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        initSharePreferences(context.applicationContext)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}
