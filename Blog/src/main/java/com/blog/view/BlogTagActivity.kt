package com.blog.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.databinding.ItemBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.viewmodel.BlogTagViewModel
import com.common.base.BaseEntity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener

/**
 * by y on 24/11/2017.
 */
class BlogTagActivity : AppCompatActivity(),
        OnItemClickListener<BlogTagModel>,
        OnBind<BlogTagModel, ItemBlogTagBinding> {

    private lateinit var mAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>
    private lateinit var mBinding: ActivityBlogTagBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_tag)
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.refreshLayout.isEnabled = false
        ViewModelProviders.of(this).get(BlogTagViewModel::class.java)
                .getBlogList()
                .observe(this,
                        Observer<BaseEntity<ObservableArrayList<BlogTagModel>>> { tagList ->
                            if (tagList != null) {
                                mBinding.refreshLayout.isRefreshing = false
                                if (tagList.data != null) {
                                    mAdapter.addAll(tagList.data!!)
                                }
                            } else {
                                mBinding.refreshLayout.isRefreshing = true
                            }
                            mBinding.executePendingBindings()
                        })
    }


    override fun onItemClick(view: View, position: Int, info: BlogTagModel) {

    }

    override fun onBind(bind: ItemBlogTagBinding, position: Int, info: BlogTagModel) {
        bind.entity = info
    }
}