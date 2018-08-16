package com.blog.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.databinding.ItemBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.viewmodel.BlogTagViewModel
import com.common.base.BaseActivity
import com.common.base.BaseEntity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.databinding.RootBinding
import com.common.utils.UIUtils
import com.status.layout.Status

/**
 * by y on 24/11/2017.
 */
class BlogTagActivity : BaseActivity<ActivityBlogTagBinding>(),
        OnItemClickListener<BlogTagModel>,
        OnBind<BlogTagModel, ItemBlogTagBinding>,
        Observer<BaseEntity<ObservableArrayList<BlogTagModel>>> {

    private lateinit var mAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = UIUtils.getString(R.string.blog_tag)
        mAdapter = DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>()
                .initLayoutId(R.layout.item_blog_tag)
                .setOnItemClickListener(this)
                .onBind(this)
        binding.layoutManager = LinearLayoutManager(this)
        binding.blogRecyclerView.setHasFixedSize(true)
        binding.blogRecyclerView.adapter = mAdapter
        ViewModelProviders.of(this).get(BlogTagViewModel::class.java).blogTag.observe(this, this)
    }

    override fun getLayoutId(): Int = R.layout.activity_blog_tag

    override fun onChanged(tagList: BaseEntity<ObservableArrayList<BlogTagModel>>?) {
        if (tagList == null) {
            onChangeStatusLayout(Status.ERROR)
            return
        }
        when (tagList.type) {
            BaseEntity.ERROR -> onChangeStatusLayout(Status.ERROR)
            BaseEntity.LOADING -> onChangeStatusLayout(Status.LOADING)
            BaseEntity.SUCCESS -> {
                onChangeStatusLayout(Status.SUCCESS)
                mAdapter.addAll(tagList.data!!)
            }
        }
    }

    override fun onItemClick(view: View, position: Int, info: BlogTagModel) {
        val bundle = Bundle()
        bundle.putString(BlogDetailActivity.TITLE, info.title)
        bundle.putString(BlogDetailActivity.URL, info.detailUrl)
        UIUtils.startActivity(BlogDetailActivity().javaClass, bundle)
    }

    override fun onBind(bind: ItemBlogTagBinding, position: Int, info: BlogTagModel) {
        bind.entity = info
    }
}