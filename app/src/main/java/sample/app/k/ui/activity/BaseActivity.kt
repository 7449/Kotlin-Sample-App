package sample.app.k.ui.activity

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import sample.app.k.R
import sample.app.k.ui.widget.status.StatusClickListener
import sample.app.k.ui.widget.status.StatusLayout


/**
 * by y on 27/09/2017.
 */
abstract class BaseActivity : AppCompatActivity(), StatusClickListener {
    protected var mStatusView: StatusLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initContentView())
        initById()
        initCreate(savedInstanceState)
    }

    private fun initContentView(): View {
        val coordinatorLayout = CoordinatorLayout(this)
        coordinatorLayout.id = R.id.activityRootView
        mStatusView = StatusLayout(this)
        mStatusView?.setSuccessView(getLayoutId(), FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        mStatusView?.setEmptyView(R.layout.layout_empty)
        mStatusView?.setErrorView(R.layout.layout_error)
        mStatusView?.getEmptyView()?.setOnClickListener({ clickNetWork() })
        mStatusView?.getErrorView()?.setOnClickListener({ clickNetWork() })
        setStatusViewStatus(StatusLayout.SUCCESS)
        coordinatorLayout.addView(mStatusView, CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        return coordinatorLayout
    }


    protected fun <T : View> getView(id: Int): T = findViewById(id) as T

    abstract fun initCreate(savedInstanceState: Bundle?)
    abstract fun initById()
    abstract fun clickNetWork()
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

    fun setStatusViewStatus(status: String) {
        if (mStatusView != null) {
            mStatusView?.setStatus(status)
        }
    }

}