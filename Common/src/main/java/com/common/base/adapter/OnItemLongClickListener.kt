package com.common.base.adapter

import android.view.View

/**
 * by y on 02/11/2017.
 */
interface OnItemLongClickListener<in T> {
    fun onItemLongClick(view: View, position: Int, info: T)
}