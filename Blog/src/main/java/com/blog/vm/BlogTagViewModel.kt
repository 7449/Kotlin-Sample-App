package com.blog.vm

import android.databinding.ObservableArrayList
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.model.JsoupManager
import com.common.net.NetApi
import com.common.net.service.SuccessCallback
import org.jsoup.nodes.Document

/**
 * by y on 24/11/2017.
 */

class BlogTagViewModel(binding: ActivityBlogTagBinding, private val call: SuccessCallback<ObservableArrayList<BlogTagModel>>) : BlogVM<ObservableArrayList<BlogTagModel>, ActivityBlogTagBinding>(binding) {

    fun onRefresh() {
        binding.refreshLayout.isRefreshing = true
        httpJsoupRequest(NetApi.BLOG_BASE_URL + NetApi.BLOG_TAG_URL, this)
    }

    override fun onHttpSuccess(info: ObservableArrayList<BlogTagModel>) {
        call.add(info)
        binding.refreshLayout.isRefreshing = false
    }

    override fun onHttpError(e: Throwable) {
        binding.refreshLayout.isRefreshing = false
    }

    override fun getT(document: Document): ObservableArrayList<BlogTagModel> = JsoupManager.getTag(document)

}
