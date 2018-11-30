package com.blog.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.blog.R
import com.blog.databinding.ActivityBlogTagBinding
import com.blog.databinding.ItemBlogTagBinding
import com.blog.model.BlogTagModel
import com.blog.viewmodel.BlogTagViewModel
import com.common.base.*
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.databinding.RootBinding
import com.common.utils.openActivity
import com.status.layout.ERROR
import com.status.layout.LOADING
import com.status.layout.SUCCESS
import io.reactivex.jsoup.network.manager.RxJsoupNetWork

/**
 * by y on 24/11/2017.
 */
class BlogTagActivity : BaseActivity<ActivityBlogTagBinding>(),
        OnItemClickListener<BlogTagModel>,
        OnBind<BlogTagModel, ItemBlogTagBinding>,
        Observer<BaseEntity<ObservableArrayList<BlogTagModel>>> {

    private lateinit var mAdapter: DataBindingAdapter<BlogTagModel, ItemBlogTagBinding>

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = getString(R.string.blog_tag)
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

    override fun onChanged(tagList: BaseEntity<ObservableArrayList<BlogTagModel>>) {
        when (tagList.type) {
            ENTITY_ERROR -> {
                onChangeStatusLayout(ERROR)
            }
            ENTITY_LOADING -> {
                onChangeStatusLayout(LOADING)
            }
            ENTITY_SUCCESS -> {
                onChangeStatusLayout(SUCCESS)
                mAdapter.addAll(tagList.data ?: ObservableArrayList())
            }
        }
    }

    override fun onItemClick(view: View, position: Int, info: BlogTagModel) {
        openActivity(BlogDetailActivity().javaClass, Bundle().apply {
            putString(BLOG_DETAIL_TITLE, info.title)
            putString(BLOG_DETAIL_URL, info.detailUrl)
        })
    }

    override fun onBind(bind: ItemBlogTagBinding, position: Int, info: BlogTagModel) {
        bind.entity = info
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancel(BlogTagViewModel::class.java.simpleName)
    }
}