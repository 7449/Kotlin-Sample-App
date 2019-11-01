package com.zhihu.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhihu.ZhiHuConstant
import com.zhihu.view.ZhiHuListFragment


/**
 * by y on 2017/2/27
 */

class ZhiHuTabAdapter(fm: FragmentManager, private val type: String) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = ZhiHuListFragment.newInstance(position, type)
    override fun getPageTitle(position: Int): CharSequence? = ZhiHuConstant.getTabName(type)[position]
    override fun getCount(): Int = ZhiHuConstant.getTabName(type).size
}
