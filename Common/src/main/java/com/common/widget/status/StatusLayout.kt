package com.common.widget.status


import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.LayoutRes
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.common.R

/**
 * by y on 14/07/2017.
 *
 *
 * 多种状态布局Layout
 *
 *
 * 不一定要把所有的状态View都填充，例如有些页面没必要设置 ErrorView，可以不用填充
 *
 *
 * 建议 在 BaseActivity 或者 BaseFragment 里面  设置 StatusLayout 为 RootView，这样想改变页面状态很简单就完成了
 *
 *
 *
 *
 * 使用示例：
 * <pre>
 * <com.status.StatusLayout android:id="@+id/statusLayout" android:layout_width="match_parent" android:layout_height="300dp" app:status_empty_layout="@layout/layout_empty" app:status_error_layout="@layout/layout_error" app:status_loading_layout="@layout/layout_loading" app:status_normal_layout="@layout/layout_normal" app:status_success_layout="@layout/layout_success"></com.status.StatusLayout>
 *
 * or
 *
 * statusLayout.setNorMalView(R.layout.layout_normal, null);
 * statusLayout.setLoadingView(R.layout.layout_loading, null);
 * statusLayout.setEmptyView(R.layout.layout_empty, null);
 * statusLayout.setSuccessView(R.layout.layout_success, null);
 * statusLayout.setErrorView(R.layout.layout_error, null);
</pre> */

class StatusLayout : FrameLayout {

    private var mStatus: String? = null


    private var clickListener: StatusClickListener? = null

    private var mNorMalView: View? = null
    private var mLoadingView: View? = null
    private var mEmptyView: View? = null
    private var mSuccessView: View? = null
    private var mErrorView: View? = null

    @LayoutRes
    private var mNormalLayoutId = NO_LAYOUT
    @LayoutRes
    private var mLoadingLayoutId = NO_LAYOUT
    @LayoutRes
    private var mEmptyLayoutId = NO_LAYOUT
    @LayoutRes
    private var mSuccessLayoutId = NO_LAYOUT
    @LayoutRes
    private var mErrorLayoutId = NO_LAYOUT

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StatusLayout)

        mNormalLayoutId = typedArray.getResourceId(R.styleable.StatusLayout_status_normal_layout, NO_LAYOUT)
        mLoadingLayoutId = typedArray.getResourceId(R.styleable.StatusLayout_status_loading_layout, NO_LAYOUT)
        mEmptyLayoutId = typedArray.getResourceId(R.styleable.StatusLayout_status_empty_layout, NO_LAYOUT)
        mSuccessLayoutId = typedArray.getResourceId(R.styleable.StatusLayout_status_success_layout, NO_LAYOUT)
        mErrorLayoutId = typedArray.getResourceId(R.styleable.StatusLayout_status_error_layout, NO_LAYOUT)


        val normalFlag = typedArray.getBoolean(R.styleable.StatusLayout_status_normal_flag, false)
        val loadingFlag = typedArray.getBoolean(R.styleable.StatusLayout_status_loading_flag, false)
        val emptyFlag = typedArray.getBoolean(R.styleable.StatusLayout_status_empty_flag, false)
        val successFlag = typedArray.getBoolean(R.styleable.StatusLayout_status_success_flag, false)
        val errorFlag = typedArray.getBoolean(R.styleable.StatusLayout_status_error_flag, false)

        if (mNormalLayoutId != NO_LAYOUT) {
            if (normalFlag) {
                setNorMalView(mNormalLayoutId)
            } else {
                setNorMalView(mNormalLayoutId, null)
            }
        }
        if (mLoadingLayoutId != NO_LAYOUT) {
            if (loadingFlag) {
                setLoadingView(mLoadingLayoutId)
            } else {
                setLoadingView(mLoadingLayoutId, null)
            }
        }
        if (mEmptyLayoutId != NO_LAYOUT) {
            if (emptyFlag) {
                setEmptyView(mEmptyLayoutId)
            } else {
                setEmptyView(mEmptyLayoutId, null)
            }
        }
        if (mSuccessLayoutId != NO_LAYOUT) {
            if (successFlag) {
                setSuccessView(mSuccessLayoutId)
            } else {
                setSuccessView(mSuccessLayoutId, null)
            }
        }
        if (mErrorLayoutId != NO_LAYOUT) {
            if (errorFlag) {
                setErrorView(mErrorLayoutId)
            } else {
                setErrorView(mErrorLayoutId, null)
            }
        }

        typedArray.recycle()

        //如果是xml设置布局，直接将所有View Gone掉，然后在代码中设置显示或者隐藏
        Util.goneView(mNorMalView, mLoadingView, mEmptyView, mSuccessView, mErrorView)
    }

    /**
     * 改变布局的状态
     *
     * @param status see[Status]
     * @return false : 当前状态和设置的状态一致.
     */
    fun setStatus(status: String): Boolean {
        if (TextUtils.equals(mStatus, status)) {
            return false
        }
        Util.goneView(mNorMalView, mLoadingView, mEmptyView, mSuccessView, mErrorView)
        when (status) {
            NORMAL -> Util.visibilityView(mNorMalView)
            LOADING -> Util.visibilityView(mLoadingView)
            EMPTY -> Util.visibilityView(mEmptyView)
            SUCCESS -> Util.visibilityView(mSuccessView)
            ERROR -> Util.visibilityView(mErrorView)
            else -> throw RuntimeException("please check status")
        }
        mStatus = status
        return true
    }


    fun setStatusClickListener(clickListener: StatusClickListener) {
        this.clickListener = clickListener
    }

    /**********************   LayoutRes  默认 params ， 填充屏幕并居中    */

    fun setNorMalView(@LayoutRes normalLayoutRes: Int) {
        setNorMalView(Util.getViewLayout(this, normalLayoutRes))
    }

    fun setLoadingView(@LayoutRes loadingLayoutRes: Int) {
        setLoadingView(Util.getViewLayout(this, loadingLayoutRes))
    }

    fun setEmptyView(@LayoutRes emptyLayoutRes: Int) {
        setEmptyView(Util.getViewLayout(this, emptyLayoutRes))
    }


    fun setSuccessView(@LayoutRes successLayoutRes: Int) {
        setSuccessView(Util.getViewLayout(this, successLayoutRes))
    }

    fun setErrorView(@LayoutRes errorLayoutRes: Int) {
        setErrorView(Util.getViewLayout(this, errorLayoutRes))
    }

    /**********************   LayoutRes, params（可为 Null）   */

    fun setNorMalView(@LayoutRes normalLayoutRes: Int, params: FrameLayout.LayoutParams?) {
        setNorMalView(Util.getViewLayout(this, normalLayoutRes), params)
    }

    fun setLoadingView(@LayoutRes loadingLayoutRes: Int, params: FrameLayout.LayoutParams?) {
        setLoadingView(Util.getViewLayout(this, loadingLayoutRes), params)
    }

    fun setEmptyView(@LayoutRes emptyLayoutRes: Int, params: FrameLayout.LayoutParams?) {
        setEmptyView(Util.getViewLayout(this, emptyLayoutRes), params)
    }


    fun setSuccessView(@LayoutRes successLayoutRes: Int, params: FrameLayout.LayoutParams?) {
        setSuccessView(Util.getViewLayout(this, successLayoutRes), params)
    }

    fun setErrorView(@LayoutRes errorLayoutRes: Int, params: FrameLayout.LayoutParams?) {
        setErrorView(Util.getViewLayout(this, errorLayoutRes), params)
    }


    /**********************   最终填充View方法  flag addView() 的时候是否 使用 params    */

    @JvmOverloads
    fun setNorMalView(norMalView: View, params: FrameLayout.LayoutParams? = Util.params) {
        if (mNorMalView != null) {
            removeView(mNorMalView)
            mNorMalView = null
        }
        this.mNorMalView = norMalView
        if (params != null) {
            addView(norMalView, params)
        } else {
            addView(norMalView)
        }
        mNorMalView!!.setOnClickListener {
            if (clickListener != null)
                clickListener!!.onNorMalClick()
        }
    }

    @JvmOverloads
    fun setLoadingView(loadingView: View, params: FrameLayout.LayoutParams? = Util.params) {
        if (mLoadingView != null) {
            removeView(mLoadingView)
            mLoadingView = null
        }
        this.mLoadingView = loadingView
        if (params != null) {
            addView(loadingView, params)
        } else {
            addView(loadingView)
        }
        mLoadingView!!.setOnClickListener {
            if (clickListener != null)
                clickListener!!.onLoadingClick()
        }
    }

    @JvmOverloads
    fun setEmptyView(emptyView: View, params: FrameLayout.LayoutParams? = Util.params) {
        if (mEmptyView != null) {
            removeView(mEmptyView)
            mEmptyView = null
        }
        this.mEmptyView = emptyView
        if (params != null) {
            addView(emptyView, params)
        } else {
            addView(emptyView)
        }
        mEmptyView!!.setOnClickListener {
            if (clickListener != null)
                clickListener!!.onEmptyClick()
        }
    }

    @JvmOverloads
    fun setSuccessView(successView: View, params: FrameLayout.LayoutParams? = Util.params) {
        if (mSuccessView != null) {
            removeView(mSuccessView)
            mSuccessView = null
        }
        this.mSuccessView = successView
        if (params != null) {
            addView(successView, params)
        } else {
            addView(successView)
        }
        mSuccessView!!.setOnClickListener {
            if (clickListener != null)
                clickListener!!.onSuccessClick()
        }
    }

    @JvmOverloads
    fun setErrorView(errorView: View, params: FrameLayout.LayoutParams? = Util.params) {
        if (mErrorView != null) {
            removeView(mErrorView)
            mErrorView = null
        }
        this.mErrorView = errorView
        if (params != null) {
            addView(errorView, params)
        } else {
            addView(errorView)
        }
        mErrorView!!.setOnClickListener {
            if (clickListener != null)
                clickListener!!.onErrorClick()
        }
    }


    /************   返回View，有可能为 Null    */

    fun getNorMalView(): View? = mNorMalView

    fun getLoadingView(): View? = mLoadingView

    fun getEmptyView(): View? = mEmptyView

    fun getSuccessView(): View? = mSuccessView

    fun getErrorView(): View? = mErrorView

    companion object {

        private val NO_LAYOUT = 0X00
        const val NORMAL = "StatusLayout:Normal" // 初始状态
        const val LOADING = "StatusLayout:Loading" // 正在加载
        const val EMPTY = "StatusLayout:Empty" // 加载空布局
        const val SUCCESS = "StatusLayout:Success" // 加载成功
        const val ERROR = "StatusLayout:Error" // 加载失败
    }

}

