package com.common.base.mvvm

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.common.base.BaseEntity
import com.socks.library.KLog
import io.reactivex.network.RxNetWorkListener

/**
 * @author y
 */
abstract class NetWorkViewModel<T> : ViewModel(), RxNetWorkListener<T> {

    val viewModelData: MediatorLiveData<BaseEntity<T>> = MediatorLiveData()

    override fun onNetWorkError(e: Throwable) {
        KLog.d(e)
    }

    override fun onNetWorkComplete() {
    }
}
