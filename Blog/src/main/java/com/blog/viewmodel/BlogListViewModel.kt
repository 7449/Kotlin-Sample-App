package com.blog.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableArrayList
import com.blog.model.BlogListModel
import com.blog.model.JsoupManager
import com.common.base.BaseEntity
import com.common.net.NetApi
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import org.jsoup.nodes.Document

/**
 * by y on 03/11/2017.
 */

class BlogListViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<ObservableArrayList<BlogListModel>> {

    private var page = 1
    val blogList: MediatorLiveData<BaseEntity<ObservableArrayList<BlogListModel>>> = MediatorLiveData()

    fun onRefresh() {
        page = 1
        RxJsoupNetWork.getInstance().cancel(javaClass.simpleName)
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL, this)
    }

    fun onLoadMore() {
        RxJsoupNetWork.getInstance().cancel(javaClass.simpleName)
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL + String.format(NetApi.BLOG_URL_SUFFIX, page), this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        blogList.value = BaseEntity(if (page == 1) BaseEntity.ERROR else BaseEntity.REFRESH_ERROR, page, ObservableArrayList())
    }

    override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)

    override fun onNetWorkStart() {
        blogList.value = BaseEntity(BaseEntity.REFRESH, page, ObservableArrayList())
    }

    override fun onNetWorkSuccess(t: ObservableArrayList<BlogListModel>?) {
        if (page == 1 && t != null && t.isEmpty()) {
            blogList.value = BaseEntity(BaseEntity.EMPTY, page, t)
        } else {
            blogList.value = BaseEntity(BaseEntity.SUCCESS, page, t)
            ++page
        }
    }
}

