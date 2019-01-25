package com.common.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.common.R
import com.status.layout.*

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
        mStatusLayout.addSuccessView(layoutId)
        mStatusLayout.addEmptyView(R.layout.layout_status_empty)
        mStatusLayout.addLoadingView(R.layout.layout_status_loading)
        mStatusLayout.addErrorView(R.layout.layout_status_error)
        mStatusLayout.setStatus(StatusLayout.SUCCESS)
        binding = DataBindingUtil.bind(mStatusLayout.getView(StatusLayout.SUCCESS) ?: View(mActivity))!!
        mStatusLayout.getView(StatusLayout.EMPTY)?.visibility = View.GONE
        mStatusLayout.getView(StatusLayout.LOADING)?.visibility = View.GONE
        mStatusLayout.getView(StatusLayout.ERROR)?.visibility = View.GONE
        mStatusLayout.onStatusClickListener = this
    }

    abstract val layoutId: Int

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
