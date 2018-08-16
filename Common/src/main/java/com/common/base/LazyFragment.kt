package com.common.base

import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author y
 */
abstract class LazyFragment<BIND : ViewDataBinding> : BaseFragment<BIND>() {

    private var isUserVisible = false
    private var isUserInitView = false
    private var isUserFirstLoad = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val convertView = super.onCreateView(inflater, container, savedInstanceState)
        isUserInitView = true
        lazyData()
        return convertView
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (isVisibleToUser) {
            isUserVisible = true
            lazyData()
        } else {
            isUserVisible = false
        }
        super.setUserVisibleHint(isVisibleToUser)
    }

    private fun lazyData() {
        if (!isUserFirstLoad || !isUserVisible || !isUserInitView) {
            return
        }
        initActivityCreated()
        isUserFirstLoad = false
    }

    protected abstract fun initActivityCreated()
}
