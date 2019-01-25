package com.zhihu.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.common.base.*
import com.common.databinding.RootBinding
import com.common.utils.ImageLoader
import com.common.utils.snackBar
import com.status.layout.StatusLayout
import com.zhihu.R
import com.zhihu.databinding.ActivityZhihuDetailBinding
import com.zhihu.model.ZhiHuDetailModel
import com.zhihu.viewmodel.ZhiHuDetailViewModel
import io.reactivex.network.RxNetWork

/**
 * @author y
 */
const val ZHIHU_DETAIL_SLUG = "slug"

class ZhiHuDetailActivity : BaseActivity<ActivityZhihuDetailBinding>(), Observer<BaseEntity<ZhiHuDetailModel>> {

    private lateinit var viewModel: ZhiHuDetailViewModel

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.toolbar.visibility = View.GONE
        setSupportActionBar(binding.zhihuDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProviders.of(this).get(ZhiHuDetailViewModel::class.java)
        viewModel.request(intent.extras?.getInt(ZHIHU_DETAIL_SLUG)
                ?: 0).zhiHuDetail.observe(this, this)
        binding.zhihuDetailFb.setOnClickListener { snackBar(binding.zhihuDetailCoordinatorLayout, R.string.zhihu_detail_fb) }
    }

    override fun onChanged(zhihuDetail: BaseEntity<ZhiHuDetailModel>) {
        when (zhihuDetail.type) {
            ENTITY_ERROR -> {
                onChangeStatusLayout(StatusLayout.ERROR)
            }
            ENTITY_LOADING -> {
                onChangeStatusLayout(StatusLayout.LOADING)
            }
            ENTITY_SUCCESS -> {
                onChangeStatusLayout(StatusLayout.SUCCESS)
                binding.title = zhihuDetail.data?.title
                ImageLoader.display(binding.zhihuDetailImage, zhihuDetail.data?.titleImage ?: "")
                binding.zhihuDetailWebView.loadDataUrl(zhihuDetail.data?.content ?: "")
            }
        }
    }

    override val layoutId: Int = R.layout.activity_zhihu_detail

    override fun onDestroy() {
        binding.zhihuDetailWebView.reset()
        RxNetWork.instance.cancel(ZhiHuDetailViewModel::class.java.simpleName)
        super.onDestroy()
    }
}

