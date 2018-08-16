package com.common.base.mvvm

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import com.common.utils.ImageLoader

/**
 * by y on 03/11/2017.
 */
object CommonView {

    @BindingAdapter("bind:isShowProgress")
    @JvmStatic
    fun isShowProgress(swipeRefreshLayout: SwipeRefreshLayout, isShowProgress: Boolean) {
        swipeRefreshLayout.isRefreshing = isShowProgress
    }

    @BindingAdapter("bind:display")
    @JvmStatic
    fun display(imageView: ImageView, url: String) {
        ImageLoader.display(imageView, url)
    }
}