package com.zhihu.viewmodel

import androidx.databinding.ObservableArrayList
import com.common.base.BaseEntity
import com.common.base.mvvm.NetWorkViewModel
import com.zhihu.model.ZhiHuListModel
import com.zhihu.model.net.server.ZLServer
import io.reactivex.network.RxNetWork

/**
 * by y on 31/10/2017.
 */
class ZhiHuListViewModel : NetWorkViewModel<ObservableArrayList<ZhiHuListModel>>() {

    fun request(suffix: String): ZhiHuListViewModel {
        RxNetWork.instance.cancel(javaClass.simpleName)
        RxNetWork.instance.getApi(javaClass.simpleName,
                RxNetWork.observable(ZLServer::class.java).getList(suffix, 20, 0), this)
        return this
    }

    override fun onNetWorkError(e: Throwable) {
        super.onNetWorkError(e)
        viewModelData.value = BaseEntity(BaseEntity.ERROR, 0, null)
    }

    override fun onNetWorkStart() {
        viewModelData.value = BaseEntity(BaseEntity.LOADING, 0, null)
    }

    override fun onNetWorkSuccess(data: ObservableArrayList<ZhiHuListModel>) {
        viewModelData.value = BaseEntity(BaseEntity.SUCCESS, 0, data)
    }
}
