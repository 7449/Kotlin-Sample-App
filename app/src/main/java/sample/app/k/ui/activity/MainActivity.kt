package sample.app.k.ui.activity

import android.os.Bundle
import android.view.View
import com.blog.view.BlogMainActivity
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.common.utils.UIUtils
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.network.RxNetWork
import sample.app.k.R
import sample.app.k.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = UIUtils.getString(R.string.app_name)
        binding.zhihu.setOnClickListener(this)
        binding.blog.setOnClickListener(this)
        binding.jsoup.setOnClickListener(this)
        binding.codeKK.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.blog -> UIUtils.startActivity(BlogMainActivity().javaClass)
//            R.id.jsoup -> UIUtils.startActivity(JsoupMainActivity().javaClass)
//            R.id.zhihu -> UIUtils.startActivity(ZhiHuMainActivity().javaClass)
//            R.id.codeKK -> UIUtils.startActivity(CodekkMainActivity().javaClass)
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancelAll()
        RxNetWork.instance.cancelAll()
    }
}
