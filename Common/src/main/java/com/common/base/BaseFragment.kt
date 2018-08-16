package com.common.base

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.common.R
import com.status.layout.OnStatusClickListener
import com.status.layout.Status
import com.status.layout.StatusLayout

/**
 * @author y
 */
abstract class BaseFragment<BIND : ViewDataBinding> : Fragment(), OnStatusClickListener {

    protected lateinit var mActivity: Activity
    protected lateinit var mStatusLayout: StatusLayout
    open var bundle: Bundle? = null
    open lateinit var binding: BIND

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mActivity = context as? Activity ?: activity!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initView(inflater)
        initCreateView(savedInstanceState)
        return mStatusLayout
    }

    abstract fun initCreateView(savedInstanceState: Bundle?)

    private fun initView(inflater: LayoutInflater) {
        mStatusLayout = StatusLayout(inflater.context)
        mStatusLayout.addSuccessView(getLayoutId())
        mStatusLayout.addEmptyView(R.layout.layout_status_empty)
        mStatusLayout.addLoadingView(R.layout.layout_status_loading)
        mStatusLayout.addErrorView(R.layout.layout_status_error)
        mStatusLayout.setStatus(Status.SUCCESS)
        binding = DataBindingUtil.bind(mStatusLayout.getView(Status.SUCCESS)!!)!!
        mStatusLayout.setOnStatusClickListener(this)
    }

    abstract fun getLayoutId(): Int

    open fun onStatusRetry() {
    }

    open fun onChangeStatusLayout(status: String) {
        mStatusLayout.setStatus(status)
    }

    override fun onSuccessClick(view: View) {
    }

    override fun onLoadingClick(view: View) {
    }

    override fun onEmptyClick(view: View) = onStatusRetry()
    override fun onErrorClick(view: View) = onStatusRetry()
    override fun onNorMalClick(view: View) {
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mStatusLayout.removeAllViews()
    }
}
