package com.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.FrameLayout
import com.common.R
import com.common.base.mvvm.CommonViewModel
import com.common.databinding.LayoutBaseDatabindingBinding
import com.common.databinding.LayoutRootBinding
import com.common.databinding.LayoutToolbarBinding
import com.common.utils.UIUtils
import com.common.widget.status.StatusClickListener
import com.common.widget.status.StatusLayout

/**
 * by y on 31/10/2017.
 */
abstract class BaseDataBindingActivity<BIND : ViewDataBinding, VM : CommonViewModel<BIND>> : AppCompatActivity(), StatusClickListener {

    /**
     * BaseViewDataBinding
     */
    private lateinit var baseBinding: LayoutBaseDatabindingBinding
    /**
     * 根布局ViewDataBinding
     */
    private lateinit var rootBinding: LayoutRootBinding
    /**
     * toolbar ViewDataBinding
     */
    private lateinit var toolbarBinding: LayoutToolbarBinding
    /**
     * 子级Activity的ViewDataBinding
     */
    protected lateinit var childDataBinding: BIND
    /**
     * 子级Activity的ViewModel
     */
    protected lateinit var childVM: VM
    /**
     * @see StatusLayout
     */
    protected lateinit var statusView: StatusLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***  初始化基类的DataBinding **/
        baseBinding = DataBindingUtil.setContentView(this, R.layout.layout_base_databinding)
        /***  设置Title **/
        baseBinding.title = getTitleName()
        /***  获取 Toolbar 的 DataBinding 和 根布局的 DataBinding **/
        rootBinding = baseBinding.layoutRoot!!
        toolbarBinding = baseBinding.layoutToolbar!!
        setSupportActionBar(toolbarBinding.toolbar)
        /***  如果是MainActivity跳过Toolbar的设置 **/
        if (!TextUtils.equals(javaClass.simpleName, "MainActivity")) {
            toolbarBinding.toolbar.navigationIcon = UIUtils.getDrawable(R.drawable.ic_arrow_back_24dp)
            toolbarBinding.toolbar.setNavigationOnClickListener { finish() }
        }
        /***  初始化根布局 **/
        initStatusView()
        /***  初始化VM **/
        childVM = initChildVm()
        /***  子类初始化 **/
        initDataBindingCreate(savedInstanceState)
    }


    private fun initStatusView() {
        statusView = rootBinding.statusLayout
        statusView.setStatusClickListener(this)
        statusView.setSuccessView(getLayoutId(), FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        statusView.setEmptyView(R.layout.layout_empty)
        statusView.setErrorView(R.layout.layout_error)
        statusView.getEmptyView()?.setOnClickListener({ clickNetWork() })
        statusView.getErrorView()?.setOnClickListener({ clickNetWork() })
        statusView.setStatus(StatusLayout.SUCCESS)
        /***  初始化子类的DataBinding **/
        childDataBinding = DataBindingUtil.bind(statusView.getSuccessView())
    }

    abstract fun initDataBindingCreate(savedInstanceState: Bundle?)
    abstract fun initChildVm(): VM
    abstract fun clickNetWork()
    abstract fun getTitleName(): String
    abstract fun getLayoutId(): Int

    override fun onNorMalClick() {
    }

    override fun onLoadingClick() {
    }

    override fun onEmptyClick() {
    }

    override fun onSuccessClick() {
    }

    override fun onErrorClick() {
    }

    override fun onDestroy() {
        super.onDestroy()
        childDataBinding.unbind()
        toolbarBinding.unbind()
        rootBinding.unbind()
        baseBinding.unbind()
        childVM.onDestroy()
    }
}
