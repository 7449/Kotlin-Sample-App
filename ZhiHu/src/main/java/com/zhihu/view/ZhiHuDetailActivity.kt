package com.zhihu.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.common.base.BaseActivity
import com.common.base.BaseEntity
import com.common.databinding.RootBinding
import com.common.utils.ImageLoader
import com.common.utils.UIUtils
import com.status.layout.Status
import com.zhihu.R
import com.zhihu.databinding.ActivityZhihuDetailBinding
import com.zhihu.model.ZhiHuDetailModel
import com.zhihu.viewmodel.ZhiHuDetailViewModel

/**
 * @author y
 */
class ZhiHuDetailActivity : BaseActivity<ActivityZhihuDetailBinding>(),
        Observer<BaseEntity<ZhiHuDetailModel>> {

    companion object {
        const val SLUG = "slug"
    }

    private lateinit var viewModel: ZhiHuDetailViewModel
    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        //其实可以在BaseActivity中Toolbar使用ViewStub加载，然后由子类决定是否加载顶部Toolbar
        rootBinding.toolbar.visibility = View.GONE
        setSupportActionBar(binding.zhihuDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(ZhiHuDetailViewModel::class.java)
        viewModel.request(intent.extras.getInt(SLUG)).zhiHuDetail.observe(this, this)
        binding.zhihuDetailFb.setOnClickListener {
            UIUtils.snackBar(binding.zhihuDetailCoordinatorLayout, R.string.zhihu_detail_fb)
        }
    }

    override fun onChanged(zhihuDetail: BaseEntity<ZhiHuDetailModel>?) {
        if (zhihuDetail == null) {
            onChangeStatusLayout(Status.ERROR)
            return
        }
        when (zhihuDetail.type) {
            BaseEntity.ERROR -> onChangeStatusLayout(Status.ERROR)
            BaseEntity.LOADING -> onChangeStatusLayout(Status.LOADING)
            BaseEntity.SUCCESS -> {
                onChangeStatusLayout(Status.SUCCESS)
                binding.title = zhihuDetail.data?.title
                ImageLoader.display(binding.zhihuDetailImage, zhihuDetail.data!!.titleImage)
                binding.zhihuDetailWebView.loadDataUrl(zhihuDetail.data!!.content)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_zhihu_detail

    override fun onDestroy() {
        binding.zhihuDetailWebView.reset()
        super.onDestroy()
    }
}

