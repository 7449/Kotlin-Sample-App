package com.zhihu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.common.base.BaseEntity
import com.common.base.ENTITY_ERROR
import com.common.base.ENTITY_LOADING
import com.common.base.ENTITY_SUCCESS
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
        RxNetWork.instance.cancel(ZhiHuDetailViewModel::class.java.simpleName)
        RxNetWork.instance.getApi(ZhiHuDetailViewModel::class.java.simpleName,
                RxNetWork.observable(ZLServer::class.java).getDetail(slug), this)
        return this
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable) {
        zhiHuDetail.value = BaseEntity(type = ENTITY_ERROR)
    }

    override fun onNetWorkStart() {
        zhiHuDetail.value = BaseEntity(type = ENTITY_LOADING)
    }

    override fun onNetWorkSuccess(data: ZhiHuDetailModel) {
        zhiHuDetail.value = BaseEntity(ENTITY_SUCCESS, 0, data)
    }
}
