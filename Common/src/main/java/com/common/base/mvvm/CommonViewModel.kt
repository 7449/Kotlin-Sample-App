package com.common.base.mvvm

import android.databinding.ViewDataBinding
import com.common.utils.LogUtils
import com.common.utils.LogUtils.tag
import io.reactivex.Observable
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import io.reactivex.network.RxNetWork
import io.reactivex.network.RxNetWorkListener

/**
 * by y on 03/11/2017.
 */
open class CommonViewModel<out BIND : ViewDataBinding>(val binding: BIND) : CommonVM {

    override fun onStart() {
    }

    override fun onDestroy() {
    }


    fun <T> httpJsoupRequest(url: String, listener: RxJsoupNetWorkListener<T>) {
        LogUtils.i("http request:${javaClass.simpleName}  url:$url")
        httpJsoupRequestCancel()
        RxJsoupNetWork.getInstance().getApi(tag, url, listener)
    }

    fun httpJsoupRequestCancel() {
        LogUtils.i("http cancel:" + javaClass.simpleName)
        RxJsoupNetWork.getInstance().cancel(javaClass.simpleName)
    }

    fun <T> httpRequest(observable: Observable<T>, listener: RxNetWorkListener<T>) {
        httpRequestCancel()
        LogUtils.i("http request:" + javaClass.simpleName)
        RxNetWork.getInstance().getApi(javaClass.simpleName, observable, listener)
    }

    fun httpRequestCancel() {
        RxNetWork.getInstance().cancel(javaClass.simpleName)
    }
}