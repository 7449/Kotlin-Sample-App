package com.blog.vm

import android.databinding.ObservableArrayList
import com.blog.databinding.ActivityMainBlogBinding
import com.blog.databinding.ItemBlogListBinding
import com.blog.model.BlogListModel
import com.blog.model.JsoupManager
import com.common.base.adapter.DataBindingAdapter
import com.common.net.NetApi
import org.jsoup.nodes.Document

/**
 * by y on 03/11/2017.
 */

class BlogListViewModel(binding: ActivityMainBlogBinding, private val adapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>) : BlogVM<ObservableArrayList<BlogListModel>, ActivityMainBlogBinding>(binding) {

    private var page = 1

    fun onRefresh() {
        binding.isShowProgress = true
        page = 1
        httpJsoupRequest(NetApi.BLOG_BASE_URL, this)
    }

    fun onLoadMore() {
        binding.isShowProgress = true
        httpJsoupRequest(NetApi.BLOG_BASE_URL + String.format(NetApi.BLOG_URL_SUFFIX, page), this)
    }

    override fun onHttpError(e: Throwable) {
        binding.isShowProgress = false
    }


    override fun onHttpSuccess(info: ObservableArrayList<BlogListModel>) {
        adapter.addAll(info)
        binding.isShowProgress = false
        page++
    }

    override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)
}
