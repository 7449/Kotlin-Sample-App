package com.common.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * by y on 25/10/2017.
 */

fun Activity.openActivity(clz: Class<*>, bundle: Bundle) {
    val intent = Intent(this, clz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Activity.openActivity(clz: Class<*>) {
    val intent = Intent(this, clz)
    startActivity(intent)
}

fun Activity.toast(`object`: Any) {
    Toast.makeText(this, `object`.toString(), Toast.LENGTH_LONG).show()
}

fun Activity.toast(id: Int) {
    Toast.makeText(this, id, Toast.LENGTH_LONG).show()
}

fun Activity.snackBar(view: View, `object`: Any) {
    Snackbar.make(view, `object`.toString(), Snackbar.LENGTH_SHORT).show()
}

fun Activity.snackBar(view: View, id: Int) {
    Snackbar.make(view, id, Snackbar.LENGTH_SHORT).show()
}

fun Activity.getStringArray(id: Int): Array<String> {
    return resources.getStringArray(id)
}

fun Fragment.openActivity(clz: Class<*>, bundle: Bundle) {
    val intent = Intent(activity, clz)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun Fragment.openActivity(clz: Class<*>) {
    val intent = Intent(activity, clz)
    startActivity(intent)
}

fun Fragment.toast(`object`: Any) {
    Toast.makeText(activity, `object`.toString(), Toast.LENGTH_LONG).show()
}

fun Fragment.toast(id: Int) {
    Toast.makeText(activity, id, Toast.LENGTH_LONG).show()
}

fun Fragment.snackBar(view: View, `object`: Any) {
    Snackbar.make(view, `object`.toString(), Snackbar.LENGTH_SHORT).show()
}

fun Fragment.snackBar(view: View, id: Int) {
    Snackbar.make(view, id, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.getStringArray(id: Int): Array<String> {
    return resources.getStringArray(id)
}