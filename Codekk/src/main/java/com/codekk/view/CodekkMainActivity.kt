package com.codekk.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.codekk.R
import com.codekk.databinding.ActivityCodekkMainBinding
import com.common.base.BaseActivity
import com.common.databinding.RootBinding
import com.common.utils.UIUtils

/**
 * by y on 31/10/2017.
 */
class CodekkMainActivity : BaseActivity<ActivityCodekkMainBinding>(),
        NavigationView.OnNavigationItemSelectedListener {
    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = UIUtils.getString(R.string.codekk_op_title)
        replaceFragment(UIUtils.getString(R.string.codekk_op_title), CodekkOpListFragment())
        binding.codekkDrawerMenu.setNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.codekk_common || item.itemId == R.id.codekk_notes) return false
        when (item.itemId) {
            R.id.codekk_op -> replaceFragment(item.title.toString(), CodekkOpListFragment())
            R.id.codekk_opa -> replaceFragment(item.title.toString(), CodekkOpaListFragment())
            R.id.codekk_job -> replaceFragment(item.title.toString(), CodekkJobListFragment())
            R.id.codekk_blog -> replaceFragment(item.title.toString(), CodekkBlogListFragment())
            R.id.codekk_recommend -> replaceFragment(item.title.toString(), CodekkRecommendListFragment())
        }
        binding.codekkDrawer.closeDrawers()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.codekk_setting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.codekk_setting -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(title: String, fragment: Any) {
        onChangeToolbarTitle(title)
        return
        supportFragmentManager.beginTransaction().replace(R.id.codekk_fragment, fragment as Fragment).commit()
    }


    override fun getLayoutId(): Int = R.layout.activity_codekk_main

}