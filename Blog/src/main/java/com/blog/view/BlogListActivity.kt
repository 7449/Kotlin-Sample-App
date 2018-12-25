package com.blog.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blog.BR
import com.blog.R
import com.blog.databinding.ActivityBlogListBinding
import com.blog.model.BlogListModel
import com.blog.viewmodel.BlogListViewModel
import com.common.base.*
import com.common.databinding.RootBinding
import com.common.utils.openActivity
import com.common.utils.toast
import com.common.widget.LoadMoreRecyclerView
import com.socks.library.KLog
import com.status.layout.EMPTY
import com.status.layout.ERROR
import com.status.layout.SUCCESS
import com.xadapter.OnItemClickListener
import com.xadapter.adapter.XDataBindingAdapter
import com.xadapter.adapter.XDataBindingAdapterFactory
import io.reactivex.jsoup.network.manager.RxJsoupNetWork

/**
 * by y on 31/10/2017.
 */
class BlogListActivity : BaseActivity<ActivityBlogListBinding>(),
        OnItemClickListener<BlogListModel>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener,
        Observer<BaseEntity<ObservableArrayList<BlogListModel>>> {

    private lateinit var mAdapter: XDataBindingAdapter<BlogListModel>
    private lateinit var viewModel: BlogListViewModel

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(BlogListViewModel::class.java)
        rootBinding.title = getString(R.string.blog_list_title)

        binding.layoutManager = LinearLayoutManager(this)
        mAdapter = XDataBindingAdapterFactory(BR.entity)
        mAdapter.apply {
            itemLayoutId = R.layout.item_blog_list
            onItemClickListener = this@BlogListActivity
        }
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

    override fun onChanged(blogList: BaseEntity<ObservableArrayList<BlogListModel>>) {
        KLog.d(blogList.type)
        when (blogList.type) {
            ENTITY_EMPTY -> {
                onChangeStatusLayout(EMPTY)
            }
            ENTITY_REFRESH_ERROR -> {
                binding.blogRefreshLayout.isRefreshing = false
            }
            ENTITY_REFRESH -> {
                binding.blogRefreshLayout.isRefreshing = true
            }
            ENTITY_ERROR -> {
                if (blogList.page == 1) {
                    onChangeStatusLayout(ERROR)
                }
            }
            ENTITY_NOMORE -> {
                toast(R.string.blog_no_more)
            }
            ENTITY_SUCCESS -> {
                if (blogList.page == 1) {
                    mAdapter.removeAll()
                }
                onChangeStatusLayout(SUCCESS)
                binding.blogRefreshLayout.isRefreshing = false
                mAdapter.addAll(blogList.data!!)
            }
        }
    }

    override fun onItemClick(view: View, position: Int, entity: BlogListModel) {
        openActivity(BlogDetailActivity().javaClass, Bundle().apply {
            putString(BLOG_DETAIL_TITLE, entity.title)
            putString(BLOG_DETAIL_URL, entity.detailUrl)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.blog_menu_tag, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.blog_tag) {
            openActivity(BlogTagActivity().javaClass)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancel(BlogListViewModel::class.java.simpleName)
    }

    override val layoutId: Int = R.layout.activity_blog_list
}