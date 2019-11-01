package com.app.k

import android.os.Bundle
import com.app.k.databinding.ActivityMainBinding
import com.blog.view.BlogListActivity
import com.codekk.view.CodekkMainActivity
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.common.utils.openActivity
import com.zhihu.view.ZhiHuMainActivity
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.network.RxNetWork

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = getString(R.string.app_name)
        binding.zhihu.setOnClickListener { openActivity(ZhiHuMainActivity().javaClass) }
        binding.blog.setOnClickListener { openActivity(BlogListActivity().javaClass) }
        binding.codeKK.setOnClickListener { openActivity(CodekkMainActivity().javaClass) }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancelAll()
        RxNetWork.instance.cancelAll()
    }
}
