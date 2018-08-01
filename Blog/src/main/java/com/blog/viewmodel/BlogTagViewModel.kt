package com.blog.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableArrayList
import com.blog.model.BlogTagModel
import com.blog.model.JsoupManager
import com.common.base.BaseEntity
import com.common.net.NetApi
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import org.jsoup.nodes.Document

/**
 * by y on 24/11/2017.
 */

class BlogTagViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<ObservableArrayList<BlogTagModel>> {

    private val mObservableBlogList: MediatorLiveData<BaseEntity<ObservableArrayList<BlogTagModel>>> = MediatorLiveData()

    init {
        RxJsoupNetWork.getInstance().getApi(javaClass.simpleName, NetApi.BLOG_BASE_URL + NetApi.BLOG_TAG_URL, this)
    }

    override fun onNetWorkComplete() {

    }

    override fun onNetWorkError(e: Throwable?) {
        mObservableBlogList.value = BaseEntity(BaseEntity.ERROR, 0, null)
    }

    override fun getT(document: Document): ObservableArrayList<BlogTagModel> = JsoupManager.getTag(document)

    override fun onNetWorkStart() {
        mObservableBlogList.value = null
    }


    override fun onNetWorkSuccess(t: ObservableArrayList<BlogTagModel>) {
        mObservableBlogList.value = BaseEntity(BaseEntity.SUCCESS, 0, t)
    }

    fun getBlogList(): LiveData<BaseEntity<ObservableArrayList<BlogTagModel>>> {
        return mObservableBlogList
    }


}
