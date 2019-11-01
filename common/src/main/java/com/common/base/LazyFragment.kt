package com.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

/**
 * @author y
 */
abstract class LazyFragment<BIND : ViewDataBinding> : BaseFragment<BIND>() {

    private var isUserVisible = true
    private var isUserFirstLoad: Boolean = false
    private var isUserInitView: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val convertView = super.onCreateView(inflater, container, savedInstanceState)
        isUserInitView = true
        return convertView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lazyData()
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUserVisible = isVisibleToUser
        if (isVisibleToUser) {
            lazyData()
        }
    }

    private fun lazyData() {
        if (isUserFirstLoad || !isUserVisible || !isUserInitView) {
            return
        }
        initActivityCreated()
        isUserFirstLoad = true
    }

    protected abstract fun initActivityCreated()
}
