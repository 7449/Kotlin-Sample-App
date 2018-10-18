package com.common.base.adapter

import androidx.databinding.ViewDataBinding

/**
 * by y on 02/11/2017.
 */
interface OnBind<in T, in BIND : ViewDataBinding> {

    fun onBind(bind: BIND, position: Int, info: T)

}