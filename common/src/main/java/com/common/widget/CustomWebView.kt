package com.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ProgressBar
import com.common.utils.HtmlUtils

/**
 * @author y
 */
@Suppress("DEPRECATION")
class CustomWebView : WebView {

    private lateinit var progressbar: ProgressBar

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun init() {
        val settings = settings
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = false
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.domStorageEnabled = true
        settings.setAppCacheEnabled(false)
        setOnLongClickListener { true }
    }

    fun openProgress() {
        progressbar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        progressbar.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 10, 0, 0)
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
        } else {
            if (progressbar.visibility == View.GONE) {
                progressbar.visibility = View.VISIBLE
            }
            progressbar.progress = newProgress
        }
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        val lp = progressbar.layoutParams as LayoutParams
        lp.x = l
        lp.y = t
        progressbar.layoutParams = lp
        super.onScrollChanged(l, t, oldl, oldt)
    }

    fun loadDataUrl(url: String) {
        loadDataWithBaseURL(null, url, HtmlUtils.mimeType, HtmlUtils.coding, null)
    }

    fun reset() {
        loadDataUrl("")
        clearHistory()
        removeAllViews()
        (parent as ViewGroup).removeView(this)
        destroy()
    }
}
