package com.blog.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityMainBlogBinding
import com.blog.databinding.ItemBlogListBinding
import com.blog.model.BlogListModel
import com.blog.viewmodel.BlogListViewModel
import com.common.base.BaseEntity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.utils.UIUtils
import com.common.widget.LoadMoreRecyclerView

/**
 * by y on 31/10/2017.
 */
class BlogMainActivity : AppCompatActivity(),
        OnItemClickListener<BlogListModel>,
        OnBind<BlogListModel, ItemBlogListBinding>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {


    private lateinit var mAdapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>
    private lateinit var mBinding: ActivityMainBlogBinding
    private lateinit var viewModel: BlogListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_blog)
        viewModel = ViewModelProviders.of(this).get(BlogListViewModel::class.java)
        mBinding.title = getString(R.string.blog)
        mBinding.layoutManager = LinearLayoutManager(this)
        mAdapter = DataBindingAdapter<BlogListModel, ItemBlogListBinding>()
                .initLayoutId(R.layout.item_blog_list)
                .setOnItemClickListener(this)
                .onBind(this)
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.setLoadingMore(this)
        mBinding.recyclerView.adapter = mAdapter
        mBinding.refreshLayout.setOnRefreshListener(this)
        viewModel
                .getBlogList()
                .observe(this,
                        Observer<BaseEntity<ObservableArrayList<BlogListModel>>> { blogList ->
                            if (blogList != null) {
                                mBinding.isShowProgress = false
                                if (blogList.page == 1) {
                                    mAdapter.removeAll()
                                }
                                if (blogList.data != null) {
                                    mAdapter.addAll(blogList.data!!)
                                }
                            } else {
                                mBinding.isShowProgress = true
                            }
                            mBinding.executePendingBindings()
                        })
    }


    override fun onRefresh() {
        viewModel.onRefresh()
    }

    override fun onLoadMore() {
        if (mBinding.refreshLayout.isRefreshing) return
        viewModel.onLoadMore()
    }

    override fun onItemClick(view: View, position: Int, info: BlogListModel) {
    }

    override fun onBind(bind: ItemBlogListBinding, position: Int, info: BlogListModel) {
        bind.entity = info
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_tag, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.tag) {
            UIUtils.startActivity(BlogTagActivity().javaClass)
        }
        return super.onOptionsItemSelected(item)
    }
}