package com.common.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup

import com.common.R
import com.google.android.flexbox.FlexboxLayout


/**
 * by y on 2017/5/18
 */

class FlowText : AppCompatTextView {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setPadding(18, 10, 18, 10)
        val params = FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.leftMargin = 10
        params.bottomMargin = 10
        gravity = Gravity.CENTER
        textSize = 10f
        setTextColor(ContextCompat.getColor(context, android.R.color.white))
        layoutParams = params
        setBackgroundResource(R.drawable.shape_tag)
    }
}
