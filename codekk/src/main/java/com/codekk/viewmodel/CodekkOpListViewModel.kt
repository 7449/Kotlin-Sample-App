package com.codekk.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.codekk.model.CodekkOpListModel
import com.codekk.model.net.NetFunc
import com.codekk.model.net.server.CodekkServer
import com.common.base.*
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
        RxNetWork.instance.cancel(CodekkOpListViewModel::class.java.simpleName)
        RxNetWork.instance.getApi(CodekkOpListViewModel::class.java.simpleName,
                RxNetWork
                        .observable(CodekkServer::class.java)
                        .getOpList(page, "array")
                        .map(NetFunc()), this)
    }

    fun onLoadMore() {
        RxNetWork.instance.cancel(CodekkOpListViewModel::class.java.simpleName)
        RxNetWork.instance.getApi(CodekkOpListViewModel::class.java.simpleName,
                RxNetWork
                        .observable(CodekkServer::class.java)
                        .getOpList(page, "array")
                        .map(NetFunc()), this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkStart() {
        opList.value = BaseEntity(ENTITY_REFRESH, page, ObservableArrayList())
    }

    override fun onNetWorkError(e: Throwable) {
        opList.value = BaseEntity(if (page == 1) ENTITY_ERROR else ENTITY_REFRESH_ERROR, page, ObservableArrayList())
    }

    override fun onNetWorkSuccess(data: CodekkOpListModel) {
        if (page != 1 && data.projectArray.isEmpty()) {
            opList.value = BaseEntity(ENTITY_NOMORE, page, data.projectArray)
            return
        }
        if (page == 1 && data.projectArray.isEmpty()) {
            opList.value = BaseEntity(ENTITY_EMPTY, page, data.projectArray)
        } else {
            opList.value = BaseEntity(ENTITY_SUCCESS, page, data.projectArray)
            ++page
        }
    }
}