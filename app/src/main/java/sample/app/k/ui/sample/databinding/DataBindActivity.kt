package sample.app.k.ui.sample.databinding

import android.os.Bundle
import com.common.base.BaseDataBindingActivity
import sample.app.k.R
import sample.app.k.databinding.ActivityDatabindBinding
import sample.app.k.ui.sample.databinding.entitiy.MainEntity

/**
 * by y on 31/10/2017.
 */
class DataBindActivity : BaseDataBindingActivity<ActivityDatabindBinding>() {


    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
        val mainEntity = MainEntity()
        mainEntity.text1 = "adapter"
        mainEntity.text2 = "网络"
        viewDataBinding.main = mainEntity
    }

    override fun initById() {
    }

    override fun clickNetWork() {
    }

    override fun showToolbar(): Boolean = true
    override fun getLayoutId(): Int = R.layout.activity_databind
}