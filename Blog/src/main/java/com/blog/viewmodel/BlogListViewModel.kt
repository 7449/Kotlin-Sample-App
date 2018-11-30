package com.blog.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.blog.model.BlogListModel
import com.blog.model.BlogUrl
import com.blog.model.JsoupManager
import com.common.base.*
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
        RxJsoupNetWork.getInstance().cancel(BlogListViewModel::class.java.simpleName)
        RxJsoupNetWork.getInstance().getApi(BlogListViewModel::class.java.simpleName, BlogUrl.BLOG_BASE_URL, this)
    }

    fun onLoadMore() {
        RxJsoupNetWork.getInstance().cancel(BlogListViewModel::class.java.simpleName)
        RxJsoupNetWork.getInstance().getApi(BlogListViewModel::class.java.simpleName, BlogUrl.BLOG_BASE_URL + String.format(BlogUrl.BLOG_URL_SUFFIX, page), this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        blogList.value = BaseEntity(if (page == 1) ENTITY_ERROR else ENTITY_REFRESH_ERROR, page, ObservableArrayList())
    }

    override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)

    override fun onNetWorkStart() {
        blogList.value = BaseEntity(ENTITY_REFRESH, page, ObservableArrayList())
    }

    override fun onNetWorkSuccess(t: ObservableArrayList<BlogListModel>) {
        if (page != 1 && t.isEmpty()) {
            blogList.value = BaseEntity(ENTITY_NOMORE, page, t)
            return
        }
        if (page == 1 && t.isEmpty()) {
            blogList.value = BaseEntity(ENTITY_EMPTY, page, t)
        } else {
            blogList.value = BaseEntity(ENTITY_SUCCESS, page, t)
            ++page
        }
    }
}

