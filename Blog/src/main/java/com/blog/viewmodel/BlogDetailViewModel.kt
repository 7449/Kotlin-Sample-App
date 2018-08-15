package com.blog.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import com.blog.model.JsoupManager
import com.common.base.BaseEntity
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import org.jsoup.nodes.Document

/**
 * by y on 24/11/2017.
 */

class BlogDetailViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<String> {
    val blogDetail: MediatorLiveData<BaseEntity<String>> = MediatorLiveData()

    fun request(url: String): BlogDetailViewModel {
        RxJsoupNetWork.getInstance().cancel(javaClass.simpleName)
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, url, this)
        return this
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        blogDetail.value = BaseEntity(BaseEntity.ERROR, 0, null)
    }

    override fun getT(document: Document): String = JsoupManager.getDetail(document)

    override fun onNetWorkStart() {
        blogDetail.value = BaseEntity(BaseEntity.LOADING, 0, null)
    }

    override fun onNetWorkSuccess(t: String?) {
        blogDetail.value = BaseEntity(BaseEntity.SUCCESS, 0, t)
    }
}
