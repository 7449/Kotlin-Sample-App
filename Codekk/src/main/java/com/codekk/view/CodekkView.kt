package com.codekk.view

import android.text.util.Linkify
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.codekk.model.CodekkOpListModel
import com.common.widget.FlowText
import com.google.android.flexbox.FlexboxLayout

/**
 * by y on 03/11/2017.
 */
object CodekkView {

    @BindingAdapter("bind:setOpTags")
    @JvmStatic
    fun setOpTags(flexBoxLayout: FlexboxLayout, tags: List<CodekkOpListModel.ProjectArrayBean.TagsBean>?) {
        flexBoxLayout.removeAllViews()
        if (tags != null) {
            val size = tags.size
            for (i in 0 until size) {
                val flowText = FlowText(flexBoxLayout.context)
                val tag = tags[i].name
                flowText.text = tag
                flexBoxLayout.addView(flowText)
            }
        }
    }


    @BindingAdapter("bind:autoLink")
    @JvmStatic
    fun autoLink(text: AppCompatTextView, web: Boolean) {
        text.autoLinkMask = if (web) Linkify.WEB_URLS else 0
    }
}