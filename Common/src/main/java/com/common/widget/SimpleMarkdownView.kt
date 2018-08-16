package com.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.AbsoluteLayout
import android.widget.ProgressBar

import com.mukesh.MarkdownView

/**
 * by y on 2017/5/17
 */

class SimpleMarkdownView : MarkdownView {

    private lateinit var progressbar: ProgressBar
    var isLoading = false
        private set

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
        progressbar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressbar.layoutParams = AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 26, 0, 0)
        addView(progressbar)
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                newProgressBar(newProgress)
            }
        }
    }


    private fun newProgressBar(newProgress: Int) {
        if (newProgress == 100) {
            progressbar.visibility = View.GONE
            isLoading = true
        } else {
            if (progressbar.visibility == View.GONE) {
                progressbar.visibility = View.VISIBLE
            }
            isLoading = false
            progressbar.progress = newProgress
        }
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        val lp = progressbar.layoutParams as AbsoluteLayout.LayoutParams
        lp.x = l
        lp.y = t
        progressbar.layoutParams = lp
        super.onScrollChanged(l, t, oldl, oldt)
    }
}
