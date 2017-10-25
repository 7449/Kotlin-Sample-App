package sample.app.k.ui.activity

import android.os.Bundle
import android.view.View
import sample.app.k.R
import sample.app.k.ui.widget.status.StatusLayout
import sample.app.k.util.UIUtils

class MainActivity : BaseActivity(), View.OnClickListener {

    private var statusView: StatusLayout? = null

    override fun onClick(v: View?) {
//        var b = false
        when (v?.id) {
//            R.id.normal -> b = statusView!!.setStatus(StatusLayout.NORMAL)
//            R.id.loading -> b = statusView!!.setStatus(StatusLayout.LOADING)
//            R.id.empty -> b = statusView!!.setStatus(StatusLayout.EMPTY)
//            R.id.success -> b = statusView!!.setStatus(StatusLayout.SUCCESS)
//            R.id.error -> b = statusView!!.setStatus(StatusLayout.ERROR)
            R.id.zhihu -> UIUtils.toast("zhihu")
            R.id.jsoup -> UIUtils.toast("jsoup")
            R.id.blog -> UIUtils.toast("blog")
            R.id.codeKK -> UIUtils.toast("codekk")
        }
//        if (b) {
//            UIUtils.toast("设置状态成功")
//        } else {
//            UIUtils.toast("设置失败，当前状态和设置的状态相同")
//        }
    }


    override fun initCreate(savedInstanceState: Bundle?) {
        statusView?.setNorMalView(R.layout.layout_normal, null)
        statusView?.setLoadingView(R.layout.layout_loading, null)
        statusView?.setEmptyView(R.layout.layout_empty, null)
        statusView?.setSuccessView(R.layout.layout_success, null)
        statusView?.setErrorView(R.layout.layout_error, null)
//        if (statusView?.setStatus(StatusLayout.NORMAL)!!) {
//            UIUtils.toast("设置状态成功")
//        } else {
//            UIUtils.toast("设置失败，当前状态和设置的状态相同")
//        }
        statusView?.setStatusClickListener(this)
    }

    override fun initById() {
        statusView = getView(R.id.statusLayout)
        findViewById(R.id.normal).setOnClickListener(this)
        findViewById(R.id.loading).setOnClickListener(this)
        findViewById(R.id.empty).setOnClickListener(this)
        findViewById(R.id.success).setOnClickListener(this)
        findViewById(R.id.error).setOnClickListener(this)
        findViewById(R.id.zhihu).setOnClickListener(this)
        findViewById(R.id.blog).setOnClickListener(this)
        findViewById(R.id.jsoup).setOnClickListener(this)
        findViewById(R.id.codeKK).setOnClickListener(this)
    }

    override fun clickNetWork() {
    }


    override fun getLayoutId(): Int = R.layout.activity_main
}
