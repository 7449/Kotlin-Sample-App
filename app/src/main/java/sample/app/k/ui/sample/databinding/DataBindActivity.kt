package sample.app.k.ui.sample.databinding

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.common.base.BaseDataBindingActivity
import com.xadapter.adapter.XRecyclerViewAdapter
import sample.app.k.R
import sample.app.k.databinding.ActivityDatabindBinding
import sample.app.k.ui.sample.databinding.entitiy.MainEntity
import java.util.*

/**
 * by y on 31/10/2017.
 */
class DataBindActivity : BaseDataBindingActivity<ActivityDatabindBinding>() {

    private lateinit var mAdapter: XRecyclerViewAdapter<MainEntity>

    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
        childDataBinding.layoutManager = LinearLayoutManager(this)

        val list = ArrayList<MainEntity>()
        list.add(MainEntity("Adapter DataBinding"))
        mAdapter = XRecyclerViewAdapter()

        mAdapter.addRecyclerView(childDataBinding.recyclerView)
                .initXData(list)
                .setLayoutId(R.layout.item_databinding)
                .onXBind { holder, _, t -> holder.setTextView(R.id.tv_content, t.text) }
        childDataBinding.recyclerView.adapter = mAdapter

    }

    override fun clickNetWork() {
    }

    override fun getTitleName(): String = "DataBindingTitle"
    override fun getLayoutId(): Int = R.layout.activity_databind
}