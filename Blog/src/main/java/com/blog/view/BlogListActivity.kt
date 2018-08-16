package com.blog.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityBlogListBinding
import com.blog.databinding.ItemBlogListBinding
import com.blog.model.BlogListModel
import com.blog.viewmodel.BlogListViewModel
import com.common.base.BaseActivity
import com.common.base.BaseEntity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.databinding.RootBinding
import com.common.utils.UIUtils
import com.common.widget.LoadMoreRecyclerView
import com.status.layout.Status

/**
 * by y on 31/10/2017.
 */
class BlogListActivity : BaseActivity<ActivityBlogListBinding>(),
        OnItemClickListener<BlogListModel>,
        OnBind<BlogListModel, ItemBlogListBinding>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener,
        Observer<BaseEntity<ObservableArrayList<BlogListModel>>> {

    private lateinit var mAdapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>
    private lateinit var viewModel: BlogListViewModel

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(BlogListViewModel::class.java)
        rootBinding.title = UIUtils.getString(R.string.blog_list_title)
        binding.layoutManager = LinearLayoutManager(this)
        mAdapter = DataBindingAdapter<BlogListModel, ItemBlogListBinding>()
                .initLayoutId(R.layout.item_blog_list)
                .setOnItemClickListener(this)
                .onBind(this)
        binding.blogRecyclerView.setHasFixedSize(true)
        binding.blogRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.blogRecyclerView.setLoadingMore(this)
        binding.blogRecyclerView.setRefreshLayout(binding.blogRefreshLayout)
        binding.blogRecyclerView.adapter = mAdapter
        binding.blogRefreshLayout.setOnRefreshListener(this)
        binding.blogRefreshLayout.post { onRefresh() }
        viewModel.blogList.observe(this, this)
    }

    override fun onRefresh() = viewModel.onRefresh()
    override fun onLoadMore() = viewModel.onLoadMore()
    override fun onChanged(blogList: BaseEntity<ObservableArrayList<BlogListModel>>?) {
        if (blogList == null) {
            onChangeStatusLayout(Status.ERROR)
            return
        }
        when (blogList.type) {
            BaseEntity.EMPTY -> onChangeStatusLayout(Status.EMPTY)
            BaseEntity.REFRESH_ERROR -> binding.blogRefreshLayout.isRefreshing = false
            BaseEntity.REFRESH -> binding.blogRefreshLayout.isRefreshing = true
            BaseEntity.ERROR -> {
                if (blogList.page == 1) {
                    onChangeStatusLayout(Status.ERROR)
                } else {
                    //
                }
            }
            BaseEntity.NOMORE -> UIUtils.toast(R.string.blog_no_more)
            BaseEntity.SUCCESS -> {
                if (blogList.page == 1) {
                    mAdapter.removeAll()
                }
                onChangeStatusLayout(Status.SUCCESS)
                binding.blogRefreshLayout.isRefreshing = false
                mAdapter.addAll(blogList.data!!)
            }
        }
    }

    override fun onItemClick(view: View, position: Int, info: BlogListModel) {
        val bundle = Bundle()
        bundle.putString(BlogDetailActivity.TITLE, info.title)
        bundle.putString(BlogDetailActivity.URL, info.detailUrl)
        UIUtils.startActivity(BlogDetailActivity().javaClass, bundle)
    }

    override fun onBind(bind: ItemBlogListBinding, position: Int, info: BlogListModel) {
        bind.entity = info
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.blog_menu_tag, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.blog_tag) {
            UIUtils.startActivity(BlogTagActivity().javaClass)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getLayoutId(): Int = R.layout.activity_blog_list
}