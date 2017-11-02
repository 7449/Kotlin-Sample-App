package com.common.base.adapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * by y on 02/11/2017.
 */
object AdapterUtils {

    @BindingAdapter("bind:imagePath")
    @JvmStatic
    fun loadImage(view: ImageView, url: Int) {
        Glide
                .with(view.context)
                .load(url)
                .into(view)
    }
}