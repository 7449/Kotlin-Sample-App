package com.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.common.App
import com.common.R

/**
 * by y on 25/10/2017.
 */
object UIUtils {
    val context: Context
        get() = App.instance

    fun getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(context, id)

    fun getColor(id: Int): Int = ContextCompat.getColor(context, id)

    fun getString(id: Int): String = context.resources.getString(id)

    fun getStringArray(id: Int): Array<String> = context.resources.getStringArray(id)

    fun startActivity(clz: Class<*>) {
        val intent = Intent(context, clz)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun startActivity(clz: Class<*>, bundle: Bundle) {
        val intent = Intent(context, clz)
        intent.putExtras(bundle)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun toast(`object`: Any) {
        Toast.makeText(context, `object`.toString(), Toast.LENGTH_LONG).show()
    }

    fun toast(id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_LONG).show()
    }

    fun snackBar(view: View, `object`: Any) {
        Snackbar.make(view, `object`.toString(), Snackbar.LENGTH_SHORT).show()
    }

    fun snackBar(view: View, id: Int) {
        Snackbar.make(view, id, Snackbar.LENGTH_SHORT).show()
    }

    fun snackBar(view: View, `object`: Any, color: Int) {
        Snackbar.make(view, `object`.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(color)
                .show()
    }

    fun share(activity: Activity, message: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(Intent.createChooser(intent, getString(R.string.share)))
    }

}