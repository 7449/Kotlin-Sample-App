package com.zhihu.view

import android.os.Bundle
import android.view.MenuItem
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.google.android.material.navigation.NavigationView
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.ActivityZhihuMainBinding

/**
 * by y on 31/10/2017.
 */
class ZhiHuMainActivity : BaseActivity<ActivityZhihuMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.zhihu_zhihu -> replaceFragment(item.title.toString(), ZhiHuConstant.ZHIHU)
            R.id.zhihu_movie -> replaceFragment(item.title.toString(), ZhiHuConstant.MOVIE)
            R.id.zhihu_music -> replaceFragment(item.title.toString(), ZhiHuConstant.MUSIC)
            R.id.zhihu_develop -> replaceFragment(item.title.toString(), ZhiHuConstant.DEVELOP)
            R.id.zhihu_book -> replaceFragment(item.title.toString(), ZhiHuConstant.BOOK)
            R.id.zhihu_internet -> replaceFragment(item.title.toString(), ZhiHuConstant.INTERNET)
        }
        binding.zhihuDrawer.closeDrawers()
        return true
    }

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = getString(R.string.zhihu_zhihu)
        binding.zhihuDrawerMenu.setNavigationItemSelectedListener(this)
        replaceFragment(getString(R.string.zhihu_zhihu), ZhiHuConstant.ZHIHU)
    }

    private fun replaceFragment(title: String, type: String) {
        onChangeToolbarTitle(title)
        supportFragmentManager.beginTransaction().replace(R.id.zhihu_fragment, ZhiHuTabFragment.newInstance(type)).commit()
    }


    override val layoutId: Int = R.layout.activity_zhihu_main
}