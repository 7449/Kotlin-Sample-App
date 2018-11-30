package com.blog.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.blog.model.BlogTagModel
import com.blog.model.BlogUrl
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

class BlogTagViewModel(application: Application) : AndroidViewModel(application), RxJsoupNetWorkListener<ObservableArrayList<BlogTagModel>> {

    val blogTag: MediatorLiveData<BaseEntity<ObservableArrayList<BlogTagModel>>> = MediatorLiveData()

    init {
        RxJsoupNetWork.getInstance().cancel(BlogTagViewModel::class.java.simpleName)
        RxJsoupNetWork.getInstance().getApi(BlogTagViewModel::class.java.simpleName, BlogUrl.BLOG_BASE_URL + BlogUrl.BLOG_TAG_URL, this)
    }

    override fun onNetWorkComplete() {
    }

    override fun onNetWorkError(e: Throwable?) {
        blogTag.value = BaseEntity(type = ENTITY_ERROR)
    }

    override fun getT(document: Document): ObservableArrayList<BlogTagModel> = JsoupManager.getTag(document)

    override fun onNetWorkStart() {
        blogTag.value = BaseEntity(type = ENTITY_LOADING)
    }

    override fun onNetWorkSuccess(t: ObservableArrayList<BlogTagModel>) {
        blogTag.value = BaseEntity(ENTITY_SUCCESS, 0, t)
    }
}
