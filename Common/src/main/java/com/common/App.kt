package com.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.yyxk.xlog.XLog

/**
 * by y on 27/09/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        XLog.init(true, BuildConfig.APPLICATION_ID)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        val instance: App
            get() = (context as App?)!!
    }
}