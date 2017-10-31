package com.blog

import android.os.Bundle
import com.common.base.BaseActivity

/**
 * by y on 31/10/2017.
 */
class BlogMainActivity : BaseActivity() {
    override fun initCreate(savedInstanceState: Bundle?) {
        mToolbar?.title = javaClass.simpleName
    }

    override fun initById() {
        mToolbar = getView(R.id.toolbar)
    }

    override fun clickNetWork() {
    }


    override fun getLayoutId(): Int = R.layout.activity_main_blog
}