package com.blog.vm

import android.databinding.ObservableArrayList
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.databinding.ItemBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.model.JsoupManager
import com.common.base.adapter.DataBindingAdapter
import com.common.net.NetApi
import org.jsoup.nodes.Document

/**
 * by y on 24/11/2017.
 */

class BlogTagViewModel(binding: ActivityBlogTagBinding) : BlogVM<ObservableArrayList<BlogTagModel>, ActivityBlogTagBinding>(binding) {

    private lateinit var mAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>


    fun setAdapter(dataBindingAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>) {
        this.mAdapter = dataBindingAdapter
    }

    fun onRefresh() {
        binding.refreshLayout.isRefreshing = true
        httpJsoupRequest(NetApi.BLOG_BASE_URL + NetApi.BLOG_TAG_URL, this)
    }

    override fun onHttpSuccess(info: ObservableArrayList<BlogTagModel>) {
        mAdapter.addAll(info)
        binding.refreshLayout.isRefreshing = false
    }

    override fun onHttpError(e: Throwable) {
        binding.refreshLayout.isRefreshing = false
    }

    override fun getT(document: Document): ObservableArrayList<BlogTagModel> = JsoupManager.getTag(document)

}
