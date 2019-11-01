package com.codekk.view

import android.os.Bundle
import com.codekk.R
import com.codekk.databinding.ActivityCodekkSettingBinding
import com.common.base.BaseActivity
import com.common.databinding.RootBinding

/**
 * @author y
 */
class CodekkSettingActivity : BaseActivity<ActivityCodekkSettingBinding>() {

    override fun initCreate(rootBinding: RootBinding, savedInstanceState: Bundle?) {
        rootBinding.title = getString(R.string.codekk_setting_title)
    }

    override val layoutId: Int = R.layout.activity_codekk_setting

}
