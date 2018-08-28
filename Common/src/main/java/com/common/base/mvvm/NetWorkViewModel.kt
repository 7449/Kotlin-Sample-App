package com.common.base.mvvm

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.common.base.BaseEntity
import com.yyxk.xlog.XLog
import io.reactivex.network.RxNetWorkListener

/**
 * @author y
 */
abstract class NetWorkViewModel<T> : ViewModel(), RxNetWorkListener<T> {

    val viewModelData: MediatorLiveData<BaseEntity<T>> = MediatorLiveData()

    override fun onNetWorkError(e: Throwable) {
        XLog.d(e)
    }

    override fun onNetWorkComplete() {
    }
}
