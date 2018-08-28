package com.zhihu.view

import android.os.Bundle
import com.common.base.BaseFragment
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.FragmentZhihuTabBinding
import com.zhihu.view.adapter.ZhiHuTabAdapter


/**
 * by y on 2016/8/7.
 */
class ZhiHuTabFragment : BaseFragment<FragmentZhihuTabBinding>() {
    companion object {
        fun newInstance(type: String): ZhiHuTabFragment {
            val tabFragment = ZhiHuTabFragment()
            val bundle = Bundle()
            bundle.putString(ZhiHuConstant.FRAGMENT_INDEX, type)
            tabFragment.arguments = bundle
            return tabFragment
        }
    }

    override fun initCreateView(savedInstanceState: Bundle?) {
        val zhiHuTabAdapter = ZhiHuTabAdapter(childFragmentManager, bundle?.getString(ZhiHuConstant.FRAGMENT_INDEX)!!)
        binding.zhihuViewPager.adapter = zhiHuTabAdapter
        binding.zhihuTabLayout.setupWithViewPager(binding.zhihuViewPager)
        binding.zhihuViewPager.offscreenPageLimit = zhiHuTabAdapter.count
    }

    override fun getLayoutId(): Int = R.layout.fragment_zhihu_tab
}
