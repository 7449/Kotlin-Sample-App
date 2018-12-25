package sample.app.k.ui.activity

import android.os.Bundle
import com.blog.view.BlogListActivity
import com.codekk.view.CodekkMainActivity
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.common.utils.openActivity
import com.zhihu.view.ZhiHuMainActivity
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.network.RxNetWork
import sample.app.k.R
import sample.app.k.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutId: Int = R.layout.activity_main

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = getString(R.string.app_name)
        binding.zhihu.setOnClickListener { openActivity(ZhiHuMainActivity().javaClass) }
        binding.blog.setOnClickListener { openActivity(BlogListActivity().javaClass) }
//        binding.jsoup.setOnClickListener { openActivity(JsoupMainActivity().javaClass) }
        binding.codeKK.setOnClickListener { openActivity(CodekkMainActivity().javaClass) }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancelAll()
        RxNetWork.instance.cancelAll()
    }
}
