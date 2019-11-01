package com.common.utils

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.common.R

/**
 * @author y
 */
object ImageLoader {
    private val options = RequestOptions()
            .error(R.drawable.ic_glide_image)
            .placeholder(R.drawable.ic_glide_image)

    fun display(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).apply(options).into(imageView)
    }
}
