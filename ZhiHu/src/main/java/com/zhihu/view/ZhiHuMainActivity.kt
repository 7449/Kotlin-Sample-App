package com.zhihu.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.common.utils.UIUtils
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.ActivityZhihuMainBinding

/**
 * by y on 31/10/2017.
 */
class ZhiHuMainActivity : BaseActivity<ActivityZhihuMainBinding>(), NavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.zhihu -> replaceFragment(item.title.toString(), ZhiHuConstant.ZHIHU)
            R.id.movie -> replaceFragment(item.title.toString(), ZhiHuConstant.MOVIE)
            R.id.music -> replaceFragment(item.title.toString(), ZhiHuConstant.MUSIC)
            R.id.develop -> replaceFragment(item.title.toString(), ZhiHuConstant.DEVELOP)
            R.id.book -> replaceFragment(item.title.toString(), ZhiHuConstant.BOOK)
            R.id.internet -> replaceFragment(item.title.toString(), ZhiHuConstant.INTERNET)
        }
        binding.zhihuDrawer.closeDrawers()
        return true
    }

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = UIUtils.getString(R.string.zhihu)
        binding.drawerMenu.setNavigationItemSelectedListener(this)
        replaceFragment(UIUtils.getString(R.string.zhihu), ZhiHuConstant.ZHIHU)
    }

    private fun replaceFragment(title: String, type: String) {
        onChangeToolbarTitle(title)
        supportFragmentManager.beginTransaction().replace(R.id.zhihu_fragment, ZhiHuTabFragment.newInstance(type)).commit()
    }


    override fun getLayoutId(): Int = R.layout.activity_zhihu_main
}