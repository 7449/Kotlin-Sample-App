package com.blog.view

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.databinding.ItemBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.vm.BlogTagViewModel
import com.common.base.BaseDataBindingActivity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.net.service.SuccessCallback

/**
 * by y on 24/11/2017.
 */
class TagActivity : BaseDataBindingActivity<ActivityBlogTagBinding, BlogTagViewModel>(),
        OnItemClickListener<BlogTagModel>,
        OnBind<BlogTagModel, ItemBlogTagBinding>, SuccessCallback<ObservableArrayList<BlogTagModel>> {

    private lateinit var mAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>


    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
        childDataBinding.layoutManager = LinearLayoutManager(this)
        mAdapter = DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>()
                .initLayoutId(R.layout.item_blog_tag)
                .setOnItemClickListener(this)
                .onBind(this)

        childDataBinding.recyclerView.setHasFixedSize(true)
        childDataBinding.recyclerView.adapter = mAdapter

        childDataBinding.refreshLayout.isEnabled = false

        childVM.onRefresh()
    }

    override fun onItemClick(view: View, position: Int, info: BlogTagModel) {

    }

    override fun onBind(bind: ItemBlogTagBinding, position: Int, info: BlogTagModel) {
        bind.entity = info
    }

    override fun add(info: ObservableArrayList<BlogTagModel>) {
        mAdapter.addAll(info)
    }

    override fun remove() {
    }

    override fun initChildVm(): BlogTagViewModel = BlogTagViewModel(childDataBinding, this)

    override fun clickNetWork() {
        childVM.onRefresh()
    }

    override fun getTitleName(): String = getString(R.string.blog_tag_title)

    override fun getLayoutId(): Int = R.layout.activity_blog_tag
}