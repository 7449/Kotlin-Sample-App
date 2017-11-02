package com.common.base.adapter

import android.view.View

/**
 * by y on 02/11/2017.
 */
interface OnItemClickListener<in T> {

    fun onItemClick(view: View, position: Int, info: T)

}