package com.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.common.net.LogInterceptor
import com.common.utils.SPUtils
import com.socks.library.KLog
import io.reactivex.network.RxNetWork

/**
 * by y on 27/09/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        KLog.init(true, BuildConfig.APPLICATION_ID)
        RxNetWork.instance.apply {
            baseUrl = "https://www.baidu.com"
            logInterceptor = LogInterceptor()
        }
        SPUtils.init(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var context: Context? = null

        val instance: App
            get() = (context as App?)!!
    }
}