package com.blog.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blog.R
import com.blog.databinding.ActivityBlogDetailBinding
import com.blog.viewmodel.BlogDetailViewModel
import com.common.base.*
import com.common.databinding.RootBinding
import com.status.layout.StatusLayout
import io.reactivex.jsoup.network.manager.RxJsoupNetWork

/**
 * @author y
 */
const val BLOG_DETAIL_TITLE = "title"
const val BLOG_DETAIL_URL = "url"

class BlogDetailActivity : BaseActivity<ActivityBlogDetailBinding>(), Observer<BaseEntity<String>> {

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = intent.extras?.getString(BLOG_DETAIL_TITLE)
        binding.blogWebView.openProgress()
        ViewModelProviders.of(this).get(BlogDetailViewModel::class.java)
                .request(intent.extras?.getString(BLOG_DETAIL_URL) ?: "")
                .blogDetail.observe(this, this)
    }

    override fun onChanged(detail: BaseEntity<String>) {
        when (detail.type) {
            ENTITY_ERROR -> {
                onChangeStatusLayout(StatusLayout.ERROR)
            }
            ENTITY_LOADING -> {
                onChangeStatusLayout(StatusLayout.LOADING)
            }
            ENTITY_SUCCESS -> {
                onChangeStatusLayout(StatusLayout.SUCCESS)
                binding.blogWebView.loadDataUrl(detail.data ?: "")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RxJsoupNetWork.getInstance().cancel(BlogDetailViewModel::class.java.simpleName)
    }

    override val layoutId: Int = R.layout.activity_blog_detail
}
