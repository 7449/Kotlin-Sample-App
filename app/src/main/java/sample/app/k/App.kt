package sample.app.k

import android.app.Application
import android.content.Context

/**
 * by y on 27/09/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        private var context: Context? = null

        val instance: App
            get() = (context as App?)!!
    }
}