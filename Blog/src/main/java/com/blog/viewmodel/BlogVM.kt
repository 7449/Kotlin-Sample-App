package com.blog.viewmodel

import android.databinding.ViewDataBinding
import com.common.base.mvvm.CommonViewModel
import com.common.utils.LogUtils
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener

/**
 * by y on 03/11/2017.
 */
abstract class BlogVM<T, out BIND : ViewDataBinding>(binding: BIND) : CommonViewModel<BIND>(binding), RxJsoupNetWorkListener<T> {


    override fun onNetWorkStart() {
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkSuccess(info: T) {
        onHttpSuccess(info)
    }

    override fun onNetWorkError(e: Throwable) {
        LogUtils.i(e.message.toString())
        onHttpError(e)
    }

    override fun onDestroy() {
        super.onDestroy()
        httpJsoupRequestCancel()
    }

    abstract fun onHttpSuccess(info: T)

    abstract fun onHttpError(e: Throwable)
}