package com.codekk.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableArrayList
import com.codekk.model.CodekkOpListModel
import com.codekk.model.net.NetFunc
import com.codekk.model.net.server.CodekkServer
import com.common.base.BaseEntity
import io.reactivex.network.RxNetWork
import io.reactivex.network.RxNetWorkListener

/**
 * by y on 31/10/2017.
 */
class CodekkOpListViewModel(application: Application) : AndroidViewModel(application), RxNetWorkListener<CodekkOpListModel> {

    private var page = 1
    val opList: MediatorLiveData<BaseEntity<ObservableArrayList<CodekkOpListModel.ProjectArrayBean>>> = MediatorLiveData()

    fun onRefresh() {
        page = 1
        RxNetWork.instance.cancel(javaClass.simpleName)
        RxNetWork.instance.getApi(javaClass.simpleName,
                RxNetWork
                        .observable(CodekkServer::class.java)
                        .getOpList(page, "array")
                        .map(NetFunc()), this)
    }

    fun onLoadMore() {
        RxNetWork.instance.cancel(javaClass.simpleName)
        RxNetWork.instance.getApi(javaClass.simpleName,
                RxNetWork
                        .observable(CodekkServer::class.java)
                        .getOpList(page, "array")
                        .map(NetFunc()), this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkStart() {
        opList.value = BaseEntity(BaseEntity.REFRESH, page, ObservableArrayList())
    }

    override fun onNetWorkError(e: Throwable) {
        opList.value = BaseEntity(if (page == 1) BaseEntity.ERROR else BaseEntity.REFRESH_ERROR, page, ObservableArrayList())
    }

    override fun onNetWorkSuccess(data: CodekkOpListModel) {
        if (page != 1 && data.projectArray.isEmpty()) {
            opList.value = BaseEntity(BaseEntity.NOMORE, page, data.projectArray)
            return
        }
        if (page == 1 && data.projectArray.isEmpty()) {
            opList.value = BaseEntity(BaseEntity.EMPTY, page, data.projectArray)
        } else {
            opList.value = BaseEntity(BaseEntity.SUCCESS, page, data.projectArray)
            ++page
        }
    }
}