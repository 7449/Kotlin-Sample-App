package sample.app.k.ui.activity

import android.os.Bundle
import android.view.View
import com.blog.view.BlogMainActivity
import com.common.base.BaseDataBindingActivity
import com.common.base.mvvm.CommonViewModel
import com.common.databinding.LayoutRootBinding
import com.common.utils.UIUtils
import sample.app.k.R
import sample.app.k.databinding.ActivityMainBinding
import sample.app.k.ui.sample.databinding.DataBindActivity

class MainActivity : BaseDataBindingActivity<ActivityMainBinding, CommonViewModel<ActivityMainBinding>>(), View.OnClickListener {

    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
        childDataBinding.statusLayout.setNorMalView(R.layout.layout_normal, null)
        childDataBinding.statusLayout.setLoadingView(R.layout.layout_loading, null)
        childDataBinding.statusLayout.setEmptyView(R.layout.layout_empty, null)
        childDataBinding.statusLayout.setSuccessView(R.layout.layout_success, null)
        childDataBinding.statusLayout.setErrorView(R.layout.layout_error, null)
        childDataBinding.statusLayout.setStatusClickListener(this)

        childDataBinding.normal.setOnClickListener(this)
        childDataBinding.loading.setOnClickListener(this)
        childDataBinding.empty.setOnClickListener(this)
        childDataBinding.success.setOnClickListener(this)
        childDataBinding.error.setOnClickListener(this)
        childDataBinding.zhihu.setOnClickListener(this)
        childDataBinding.blog.setOnClickListener(this)
        childDataBinding.jsoup.setOnClickListener(this)
        childDataBinding.codeKK.setOnClickListener(this)
        childDataBinding.databinding.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
//        var b = false
        when (v?.id) {
//            R.id.normal -> b = childDataBinding.statusLayout.setStatus(StatusLayout.NORMAL)
//            R.id.loading -> b = childDataBinding.statusLayout.setStatus(StatusLayout.LOADING)
//            R.id.empty -> b = childDataBinding.statusLayout.setStatus(StatusLayout.EMPTY)
//            R.id.success -> b = childDataBinding.statusLayout.setStatus(StatusLayout.SUCCESS)
//            R.id.error -> b = childDataBinding.statusLayout.setStatus(StatusLayout.ERROR)
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

    override fun clickNetWork() {
    }

    override fun initChildVm(rootBinding: LayoutRootBinding): CommonViewModel<ActivityMainBinding> = CommonViewModel(childDataBinding)
    override fun getTitleName(): String = javaClass.simpleName
    override fun getLayoutId(): Int = R.layout.activity_main
}
