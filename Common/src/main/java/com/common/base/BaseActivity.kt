package com.common.base

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.common.R
import com.common.utils.UIUtils
import com.common.widget.status.StatusClickListener
import com.common.widget.status.StatusLayout


/**
 * by y on 27/09/2017.
 */
abstract class BaseActivity : AppCompatActivity(), StatusClickListener {
    protected lateinit var mStatusView: StatusLayout
    protected lateinit var mToolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initById()
        initCreate(savedInstanceState)
        mToolbar.visibility = if (showToolbar()) View.VISIBLE else View.GONE
    }

    private fun initContentView(): View {
        val coordinatorLayout = CoordinatorLayout(this)
        val linearLayout = LinearLayout(this)
        val appBarLayout = AppBarLayout(this)
        coordinatorLayout.id = R.id.activityRootView
        linearLayout.id = R.id.activityRootLinearView
        linearLayout.orientation = LinearLayout.VERTICAL

        mToolbar = Toolbar(this)
        if (!TextUtils.equals(javaClass.simpleName, "MainActivity")) {
            mToolbar.navigationIcon = UIUtils.getDrawable(R.drawable.ic_arrow_back_24dp)
            mToolbar.setNavigationOnClickListener({ finish() })
        }
        mToolbar.setTitleTextColor(UIUtils.getColor(R.color.colorWhite))
        mStatusView = StatusLayout(this)
        mStatusView.setSuccessView(getLayoutId(), FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mStatusView.setEmptyView(R.layout.layout_empty)
        mStatusView.setErrorView(R.layout.layout_error)
        mStatusView.getEmptyView()?.setOnClickListener { clickNetWork() }
        mStatusView.getErrorView()?.setOnClickListener { clickNetWork() }
        setStatusViewStatus(StatusLayout.SUCCESS)

        appBarLayout.addView(mToolbar)
        linearLayout.addView(appBarLayout, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        linearLayout.addView(mStatusView, LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        coordinatorLayout.addView(linearLayout, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        return coordinatorLayout
    }


    abstract fun initCreate(savedInstanceState: Bundle?)
    abstract fun initById()
    abstract fun clickNetWork()
    abstract fun getLayoutId(): Int
    abstract fun showToolbar(): Boolean

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

    fun setStatusViewStatus(status: String) {
        mStatusView.setStatus(status)
    }
}