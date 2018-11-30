package com.zhihu.viewmodel

import androidx.databinding.ObservableArrayList
import com.common.base.BaseEntity
import com.common.base.ENTITY_ERROR
import com.common.base.ENTITY_LOADING
import com.common.base.ENTITY_SUCCESS
import com.common.base.mvvm.NetWorkViewModel
import com.zhihu.model.ZhiHuListModel
import com.zhihu.model.net.server.ZLServer
import io.reactivex.network.RxNetWork

/**
 * by y on 31/10/2017.
 */
class ZhiHuListViewModel : NetWorkViewModel<ObservableArrayList<ZhiHuListModel>>() {

    fun request(suffix: String): ZhiHuListViewModel {
        RxNetWork.instance.cancel(ZhiHuListViewModel::class.java.simpleName)
        RxNetWork.instance.getApi(ZhiHuListViewModel::class.java.simpleName,
                RxNetWork.observable(ZLServer::class.java).getList(suffix, 20, 0), this)
        return this
    }

    override fun onNetWorkError(e: Throwable) {
        super.onNetWorkError(e)
        viewModelData.value = BaseEntity(type = ENTITY_ERROR)
    }

    override fun onNetWorkStart() {
        viewModelData.value = BaseEntity(type = ENTITY_LOADING)
    }

    override fun onNetWorkSuccess(data: ObservableArrayList<ZhiHuListModel>) {
        viewModelData.value = BaseEntity(ENTITY_SUCCESS, 0, data)
    }
}
