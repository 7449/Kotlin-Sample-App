package com.zhihu.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableArrayList
import com.common.base.BaseEntity
import com.zhihu.model.ZhiHuListModel
import com.zhihu.model.net.service.ZLService
import io.reactivex.network.RxNetWork
import io.reactivex.network.RxNetWorkListener

/**
 * by y on 31/10/2017.
 */
class ZhiHuListViewModel(application: Application) : AndroidViewModel(application), RxNetWorkListener<ObservableArrayList<ZhiHuListModel>> {

    val zhiHuList: MediatorLiveData<BaseEntity<ObservableArrayList<ZhiHuListModel>>> = MediatorLiveData()

    fun request(suffix: String): ZhiHuListViewModel {
        RxNetWork.instance.cancel(javaClass.simpleName)
        RxNetWork.instance.getApi(javaClass.simpleName,
                RxNetWork.observable(ZLService::class.java).getList(suffix, 20, 0), this)
        return this
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable) {
        zhiHuList.value = BaseEntity(BaseEntity.ERROR, 0, null)
    }

    override fun onNetWorkStart() {
        zhiHuList.value = BaseEntity(BaseEntity.LOADING, 0, null)
    }

    override fun onNetWorkSuccess(data: ObservableArrayList<ZhiHuListModel>) {
        zhiHuList.value = BaseEntity(BaseEntity.SUCCESS, 0, data)
    }
}
