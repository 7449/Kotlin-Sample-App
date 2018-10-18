package com.blog.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.blog.R
import com.blog.databinding.ActivityBlogDetailBinding
import com.blog.viewmodel.BlogDetailViewModel
import com.common.base.BaseActivity
import com.common.base.BaseEntity
import com.common.databinding.RootBinding
import com.status.layout.Status

/**
 * @author y
 */
class BlogDetailActivity : BaseActivity<ActivityBlogDetailBinding>(), Observer<BaseEntity<String>> {

    companion object {
        const val TITLE = "title"
        const val URL = "url"
    }

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = intent.extras.getString(TITLE)
        binding.blogWebView.openProgress()
        ViewModelProviders.of(this).get(BlogDetailViewModel::class.java)
                .request(intent.extras.getString(URL))
                .blogDetail.observe(this, this)
    }

    override fun onChanged(detail: BaseEntity<String>?) {
        when (detail?.type) {
            BaseEntity.ERROR -> onChangeStatusLayout(Status.ERROR)
            BaseEntity.LOADING -> onChangeStatusLayout(Status.LOADING)
            BaseEntity.SUCCESS -> {
                onChangeStatusLayout(Status.SUCCESS)
                binding.blogWebView.loadDataUrl(detail.data!!)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_blog_detail
}
