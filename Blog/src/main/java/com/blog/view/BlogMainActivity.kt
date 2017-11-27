package com.blog.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.blog.R
import com.blog.databinding.ActivityMainBlogBinding
import com.blog.databinding.ItemBlogListBinding
import com.blog.model.BlogListModel
import com.blog.vm.BlogListViewModel
import com.common.base.BaseDataBindingActivity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.databinding.LayoutRootBinding
import com.common.utils.UIUtils
import com.common.widget.LoadMoreRecyclerView

/**
 * by y on 31/10/2017.
 */
class BlogMainActivity : BaseDataBindingActivity<ActivityMainBlogBinding, BlogListViewModel>(),
        OnItemClickListener<BlogListModel>,
        OnBind<BlogListModel, ItemBlogListBinding>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener {


    private lateinit var mAdapter: DataBindingAdapter<BlogListModel, ItemBlogListBinding>


    override fun initDataBindingCreate(savedInstanceState: Bundle?) {

        childDataBinding.layoutManager = LinearLayoutManager(this)
        mAdapter = DataBindingAdapter<BlogListModel, ItemBlogListBinding>()
                .initLayoutId(R.layout.item_blog_list)
                .setOnItemClickListener(this)
                .onBind(this)

        childVM.setAdapter(mAdapter)

        childDataBinding.recyclerView.setHasFixedSize(true)
        childDataBinding.recyclerView.setLoadingMore(this)
        childDataBinding.recyclerView.adapter = mAdapter

        childDataBinding.refreshLayout.setOnRefreshListener(this)
        childDataBinding.refreshLayout.post { this.onRefresh() }


    }

    override fun onRefresh() {
        childVM.onRefresh()
    }

    override fun onLoadMore() {
        childVM.onLoadMore()
    }

    override fun onItemClick(view: View, position: Int, info: BlogListModel) {
    }

    override fun onBind(bind: ItemBlogListBinding, position: Int, info: BlogListModel) {
        bind.entity = info
    }

    override fun clickNetWork() {
        childVM.onRefresh()
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

    override fun initChildVm(rootBinding: LayoutRootBinding): BlogListViewModel = BlogListViewModel(childDataBinding, rootBinding)
    override fun getTitleName(): String = getString(R.string.blog)
    override fun getLayoutId(): Int = R.layout.activity_main_blog
}