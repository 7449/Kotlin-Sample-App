package com.common.base

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.common.R
import com.common.databinding.RootBinding
import com.socks.library.KLog
import com.status.layout.OnStatusClickListener
import com.status.layout.Status

/**
 * by y on 31/10/2017.
 */
abstract class BaseActivity<BIND : ViewDataBinding> : AppCompatActivity(), OnStatusClickListener {
    companion object {
        private const val MAIN_CLASS_NAME = "MainActivity"
    }

    open lateinit var rootBinding: RootBinding
    open lateinit var binding: BIND

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = DataBindingUtil.setContentView(this, R.layout.root)
        if (!TextUtils.equals(javaClass.simpleName, MAIN_CLASS_NAME)) {
            setSupportActionBar(rootBinding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        rootBinding.statusLayout.setOnStatusClickListener(this)
        rootBinding.statusLayout.addSuccessView(getLayoutId())
        binding = DataBindingUtil.bind(rootBinding.statusLayout.getView(Status.SUCCESS))!!
        initCreate(rootBinding, savedInstanceState)
    }

    abstract fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?)
    abstract fun getLayoutId(): Int

    open fun onStatusRetry() {
    }

    override fun onSuccessClick(view: View) {
    }

    override fun onLoadingClick(view: View) {
    }

    override fun onEmptyClick(view: View) = onStatusRetry()
    override fun onErrorClick(view: View) = onStatusRetry()
    override fun onNorMalClick(view: View) {
    }


    open fun onChangeStatusLayout(status: String) {
        KLog.d(status)
        rootBinding.statusLayout.setStatus(status)
    }

    open fun onChangeToolbarTitle(title: String) {
        rootBinding.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
        rootBinding.unbind()
    }

}
