package com.blog.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
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
 * 有空了改成 dagger2
 */

class BlogListViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<ObservableArrayList<BlogListModel>> {

    private var page = 1
    private val mObservableBlogList: MediatorLiveData<BaseEntity<ObservableArrayList<BlogListModel>>> = MediatorLiveData()

    init {
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL, this)
    }

    fun onRefresh() {
        page = 1
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL, this)
    }

    fun onLoadMore() {
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL + String.format(NetApi.BLOG_URL_SUFFIX, page), this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        mObservableBlogList.value = BaseEntity(BaseEntity.ERROR, page, null)
    }

    override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)

    override fun onNetWorkStart() {
        mObservableBlogList.value = null
    }

    override fun onNetWorkSuccess(t: ObservableArrayList<BlogListModel>?) {
        mObservableBlogList.value = BaseEntity(BaseEntity.SUCCESS, page, t)
        page++
    }

    fun getBlogList(): LiveData<BaseEntity<ObservableArrayList<BlogListModel>>> {
        return mObservableBlogList
    }
}

