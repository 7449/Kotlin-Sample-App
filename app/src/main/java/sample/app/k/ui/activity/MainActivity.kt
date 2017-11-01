package sample.app.k.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.BlogMainActivity
import com.common.base.BaseActivity
import com.common.utils.UIUtils
import com.common.widget.status.StatusLayout
import sample.app.k.R
import sample.app.k.ui.sample.databinding.DataBindActivity

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var statusView: StatusLayout

    override fun onClick(v: View?) {
//        var b = false
        when (v?.id) {
//            R.id.normal -> b = statusView.setStatus(StatusLayout.NORMAL)
//            R.id.loading -> b = statusView.setStatus(StatusLayout.LOADING)
//            R.id.empty -> b = statusView.setStatus(StatusLayout.EMPTY)
//            R.id.success -> b = statusView.setStatus(StatusLayout.SUCCESS)
//            R.id.error -> b = statusView.setStatus(StatusLayout.ERROR)
            R.id.blog -> UIUtils.startActivity(BlogMainActivity().javaClass)
//            R.id.jsoup -> UIUtils.startActivity(JsoupMainActivity().javaClass)
//            R.id.zhihu -> UIUtils.startActivity(ZhiHuMainActivity().javaClass)
//            R.id.codeKK -> UIUtils.startActivity(CodekkMainActivity().javaClass)
            R.id.databinding -> UIUtils.startActivity(DataBindActivity().javaClass)
        }
//        if (b) {
//            UIUtils.toast("设置状态成功")
//        } else {
//            UIUtils.toast("设置失败，当前状态和设置的状态相同")
//        }
    }


    override fun initCreate(savedInstanceState: Bundle?) {
        statusView.setNorMalView(R.layout.layout_normal, null)
        statusView.setLoadingView(R.layout.layout_loading, null)
        statusView.setEmptyView(R.layout.layout_empty, null)
        statusView.setSuccessView(R.layout.layout_success, null)
        statusView.setErrorView(R.layout.layout_error, null)
//        if (statusView.setStatus(StatusLayout.NORMAL)!!) {
//            UIUtils.toast("设置状态成功")
//        } else {
//            UIUtils.toast("设置失败，当前状态和设置的状态相同")
//        }
        statusView.setStatusClickListener(this)
    }

    override fun initById() {
        statusView = getView(R.id.statusLayout)
        findViewById<Button>(R.id.normal).setOnClickListener(this)
        findViewById<Button>(R.id.loading).setOnClickListener(this)
        findViewById<Button>(R.id.empty).setOnClickListener(this)
        findViewById<Button>(R.id.success).setOnClickListener(this)
        findViewById<Button>(R.id.error).setOnClickListener(this)
        findViewById<Button>(R.id.zhihu).setOnClickListener(this)
        findViewById<Button>(R.id.blog).setOnClickListener(this)
        findViewById<Button>(R.id.jsoup).setOnClickListener(this)
        findViewById<Button>(R.id.codeKK).setOnClickListener(this)
        findViewById<Button>(R.id.databinding).setOnClickListener(this)
    }

    override fun clickNetWork() {
    }

    override fun showToolbar(): Boolean = false
    override fun getLayoutId(): Int = R.layout.activity_main
}
