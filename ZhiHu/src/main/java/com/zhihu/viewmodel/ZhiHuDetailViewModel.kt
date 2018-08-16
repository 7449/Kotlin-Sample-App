package com.zhihu.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.common.base.BaseEntity
import com.zhihu.model.ZhiHuDetailModel
import com.zhihu.model.net.server.ZLServer
import io.reactivex.network.RxNetWork
import io.reactivex.network.RxNetWorkListener

/**
 * by y on 31/10/2017.
 */
class ZhiHuDetailViewModel(application: Application) : AndroidViewModel(application), RxNetWorkListener<ZhiHuDetailModel> {

    val zhiHuDetail: MediatorLiveData<BaseEntity<ZhiHuDetailModel>> = MediatorLiveData()

    fun request(slug: Int): ZhiHuDetailViewModel {
        RxNetWork.instance.cancel(javaClass.simpleName)
        RxNetWork.instance.getApi(javaClass.simpleName,
                RxNetWork.observable(ZLServer::class.java).getDetail(slug), this)
        return this
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable) {
        zhiHuDetail.value = BaseEntity(BaseEntity.ERROR, 0, null)
    }

    override fun onNetWorkStart() {
        zhiHuDetail.value = BaseEntity(BaseEntity.LOADING, 0, null)
    }

    override fun onNetWorkSuccess(data: ZhiHuDetailModel) {
        zhiHuDetail.value = BaseEntity(BaseEntity.SUCCESS, 0, data)
    }
}
