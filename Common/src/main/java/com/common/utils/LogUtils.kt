package com.common.utils

import android.util.Log

/**
 * by y on 02/11/2017.
 */
object LogUtils {

    val tag = "KotlinSampleLog"

    fun v(message: String) {
        v(tag, message)
    }

    fun d(message: String) {
        d(tag, message)
    }

    fun i(message: String) {
        i(tag, message)
    }

    fun w(message: String) {
        w(tag, message)
    }

    fun e(message: String) {
        e(tag, message)
    }

    fun v(tag: String, message: String) {
        Log.v(tag, message)
    }

    fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun i(tag: String, message: String) {
        Log.i(tag, message)
    }

    fun w(tag: String, message: String) {
        Log.w(tag, message)
    }

    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

}