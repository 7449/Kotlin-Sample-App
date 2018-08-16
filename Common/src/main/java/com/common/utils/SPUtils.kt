package com.common.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * by y on 2017/5/18
 */

object SPUtils {

    private const val SHAREDPREFERENCES_NAME = "kotlin.app"
    private var sharedPreferences: SharedPreferences? = null

    private val isNull: Boolean
        get() = sharedPreferences == null

    private fun initSharePreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun init(context: Context) {
        initSharePreferences(context.applicationContext)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (isNull) {
            defaultValue
        } else sharedPreferences!!.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        if (isNull) {
            return
        }
        sharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String? {
        return if (isNull) {
            defaultValue
        } else sharedPreferences!!.getString(key, defaultValue)
    }

    fun setString(key: String, value: String) {
        if (isNull) {
            return
        }
        sharedPreferences!!.edit().putString(key, value).apply()
    }

    fun clearAll() {
        if (isNull) {
            return
        }
        sharedPreferences!!.edit().clear().apply()
    }

}
