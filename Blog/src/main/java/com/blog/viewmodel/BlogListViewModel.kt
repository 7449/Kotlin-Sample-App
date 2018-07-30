package com.blog.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.databinding.ObservableArrayList
import com.blog.model.BlogListModel
import com.blog.model.JsoupManager
import com.common.net.NetApi
import com.common.utils.LogUtils
import io.reactivex.jsoup.network.manager.RxJsoupNetWork
import io.reactivex.jsoup.network.manager.RxJsoupNetWorkListener
import org.jsoup.nodes.Document

/**
 * by y on 03/11/2017.
 */

class BlogListViewModel(application: Application) : AndroidViewModel(application) {

    private var page = 1
    private val mObservableBlogList: MediatorLiveData<ObservableArrayList<BlogListModel>> = MediatorLiveData()

    init {
        page = 1
        RxJsoupNetWork
                .getInstance()
                .getApi(javaClass.simpleName,
                        NetApi.BLOG_BASE_URL,
                        object : RxJsoupNetWorkListener<ObservableArrayList<BlogListModel>> {
                            override fun onNetWorkComplete() {
                            }

                            override fun onNetWorkError(e: Throwable?) {
                            }

                            override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)

                            override fun onNetWorkStart() {
                                mObservableBlogList.postValue(null)
                            }

                            override fun onNetWorkSuccess(t: ObservableArrayList<BlogListModel>) {
                                LogUtils.d(t.size.toString())
                                mObservableBlogList.postValue(t)
                            }

                        })
    }

    fun getBlogList(): LiveData<ObservableArrayList<BlogListModel>> {
        return mObservableBlogList
    }
}

