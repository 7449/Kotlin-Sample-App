package com.blog.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.blog.model.JsoupManager
import com.common.base.BaseEntity
import com.common.base.ENTITY_ERROR
import com.common.base.ENTITY_LOADING
import com.common.base.ENTITY_SUCCESS
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import org.jsoup.nodes.Document

/**
 * by y on 24/11/2017.
 */

class BlogDetailViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<String> {
    val blogDetail: MediatorLiveData<BaseEntity<String>> = MediatorLiveData()

    fun request(url: String): BlogDetailViewModel {
        RxJsoupNetWork.getInstance().cancel(BlogDetailViewModel::class.java.simpleName)
        RxJsoupNetWork.getInstance().getApi(BlogDetailViewModel::class.java.simpleName, url, this)
        return this
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        blogDetail.value = BaseEntity(type = ENTITY_ERROR)
    }

    override fun getT(document: Document): String = JsoupManager.getDetail(document)

    override fun onNetWorkStart() {
        blogDetail.value = BaseEntity(type = ENTITY_LOADING)
    }

    override fun onNetWorkSuccess(t: String) {
        blogDetail.value = BaseEntity(ENTITY_SUCCESS, 0, t)
    }
}
