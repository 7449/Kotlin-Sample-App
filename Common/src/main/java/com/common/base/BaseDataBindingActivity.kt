package com.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle

/**
 * by y on 31/10/2017.
 */
abstract class BaseDataBindingActivity<B : ViewDataBinding> : BaseActivity() {

    protected lateinit var viewDataBinding: B

    override fun initCreate(savedInstanceState: Bundle?) {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initDataBindingCreate(savedInstanceState)
    }

    abstract fun initDataBindingCreate(savedInstanceState: Bundle?)
}