package com.common.base.mvvm

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout

/**
 * by y on 03/11/2017.
 */
object CommonView {

    @BindingAdapter("bind:isShowProgress")
    @JvmStatic
    fun isShowProgress(swipeRefreshLayout: SwipeRefreshLayout, isShowProgress: Boolean) {
        swipeRefreshLayout.isRefreshing = isShowProgress
    }
}