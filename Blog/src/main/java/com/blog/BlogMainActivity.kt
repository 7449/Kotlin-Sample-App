package com.blog

import android.os.Bundle
import com.blog.databinding.ActivityMainBlogBinding
import com.common.base.BaseDataBindingActivity

/**
 * by y on 31/10/2017.
 */
class BlogMainActivity : BaseDataBindingActivity<ActivityMainBlogBinding>() {
    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
    }

    override fun clickNetWork() {
    }

    override fun getTitleName(): String = javaClass.simpleName
    override fun getLayoutId(): Int = R.layout.activity_main_blog
}