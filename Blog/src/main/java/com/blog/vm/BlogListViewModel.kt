package com.blog.vm

import android.databinding.ObservableArrayList
import com.blog.databinding.ActivityMainBlogBinding
import com.blog.databinding.ItemBlogListBinding
import com.blog.model.BlogListModel
import com.blog.model.JsoupManager
import com.common.base.adapter.DataBindingAdapter
import com.common.databinding.LayoutRootBinding
import com.common.net.NetApi
import org.jsoup.nodes.Document

/**
 * by y on 03/11/2017.
 */

class BlogListViewModel(binding: ActivityMainBlogBinding, private var statusBinding: LayoutRootBinding) : BlogVM<ObservableArrayList<BlogListModel>, ActivityMainBlogBinding>(binding) {

    private var page = 1
    private lateinit var mAdapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>

    fun setAdapter(dataBindingAdapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>) {
        this.mAdapter = dataBindingAdapter
    }

    fun onRefresh() {
        page = 1
        showProgress()
        httpJsoupRequest(NetApi.BLOG_BASE_URL, this)
    }

    fun onLoadMore() {
        if (binding.refreshLayout.isRefreshing) {
            return
        }
        showProgress()
        httpJsoupRequest(NetApi.BLOG_BASE_URL + String.format(NetApi.BLOG_URL_SUFFIX, page), this)
    }

    override fun onHttpError(e: Throwable) {
        hideProgress()
    }

    override fun onHttpSuccess(info: ObservableArrayList<BlogListModel>) {
        if (page == 1) {
            mAdapter.removeAll()
        }
        mAdapter.addAll(info)
        page++
        hideProgress()
    }

    override fun getT(document: Document): ObservableArrayList<BlogListModel> = JsoupManager.getBlogList(document)


    private fun showProgress() {
        binding.isShowProgress = true
    }

    private fun hideProgress() {
        binding.isShowProgress = false
    }

}
