package com.jsoup

import android.os.Bundle
import com.common.base.BaseActivity

/**
 * by y on 31/10/2017.
 */
class JsoupMainActivity : BaseActivity() {
    override fun initCreate(savedInstanceState: Bundle?) {
        mToolbar.title = javaClass.simpleName
    }

    override fun initById() {
    }

    override fun clickNetWork() {
    }

    override fun showToolbar(): Boolean = true
    override fun getLayoutId(): Int = R.layout.activity_main_jsoup
}