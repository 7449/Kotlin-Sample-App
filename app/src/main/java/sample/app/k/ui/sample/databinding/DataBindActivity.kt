package sample.app.k.ui.sample.databinding

import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.common.base.BaseDataBindingActivity
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.base.mvvm.CommonViewModel
import com.common.databinding.LayoutRootBinding
import com.xadapter.adapter.XRecyclerViewAdapter
import sample.app.k.R
import sample.app.k.databinding.ActivityDatabindBinding
import sample.app.k.databinding.ItemSimpleAdapterBinding
import sample.app.k.ui.sample.databinding.entitiy.DataBindingMainEntity
import sample.app.k.ui.sample.databinding.entitiy.SimpleAdapterEntity

/**
 * by y on 31/10/2017.
 */
class DataBindActivity :
        BaseDataBindingActivity<ActivityDatabindBinding, CommonViewModel<ActivityDatabindBinding>>(),
        OnItemClickListener<SimpleAdapterEntity>,
        OnBind<SimpleAdapterEntity, ItemSimpleAdapterBinding> {


    private lateinit var mAdapter: XRecyclerViewAdapter<DataBindingMainEntity>
    private lateinit var simpleAdapter: DataBindingAdapter<SimpleAdapterEntity, ItemSimpleAdapterBinding>

    override fun initDataBindingCreate(savedInstanceState: Bundle?) {
        childDataBinding.layoutManager = LinearLayoutManager(this)
        childDataBinding.simpleLayoutManager = LinearLayoutManager(this)
        val list = ArrayList<DataBindingMainEntity>()
        list.add(DataBindingMainEntity("Adapter DataBinding"))
        mAdapter = XRecyclerViewAdapter()

        mAdapter.addRecyclerView(childDataBinding.recyclerView)
                .initXData(list)
                .setLayoutId(R.layout.item_databinding)
                .onXBind { holder, _, t -> holder.setTextView(R.id.tv_content, t.text) }
        childDataBinding.recyclerView.adapter = mAdapter


        val simpleData = ObservableArrayList<SimpleAdapterEntity>()
        (0..30).mapTo(simpleData) { SimpleAdapterEntity(it.toString(), R.mipmap.ic_launcher) }

        simpleAdapter = DataBindingAdapter<SimpleAdapterEntity, ItemSimpleAdapterBinding>()
                .addAll(simpleData)
                .initLayoutId(R.layout.item_simple_adapter)
                .setOnItemClickListener(this)
                .onBind(this)
        childDataBinding.simpleRecyclerView.adapter = simpleAdapter
        childDataBinding.btnUpdateData.setOnClickListener {
            val simpleData = ObservableArrayList<SimpleAdapterEntity>()
            simpleData.add(SimpleAdapterEntity("31", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("32", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("33", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("34", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("35", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("36", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("37", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("38", R.mipmap.ic_launcher))
            simpleData.add(SimpleAdapterEntity("39", R.mipmap.ic_launcher))
            simpleAdapter.addAll(simpleData)
        }
    }

    override fun onBind(bind: ItemSimpleAdapterBinding, position: Int, info: SimpleAdapterEntity) {
        bind.entity = info
    }

    override fun onItemClick(view: View, position: Int, info: SimpleAdapterEntity) {
    }

    override fun clickNetWork() {
    }

    override fun initChildVm(rootBinding: LayoutRootBinding): CommonViewModel<ActivityDatabindBinding> = CommonViewModel(childDataBinding)
    override fun getTitleName(): String = "DataBindingTitle"
    override fun getLayoutId(): Int = R.layout.activity_databind

    override fun onDestroy() {
        super.onDestroy()
        simpleAdapter.onDestroy()
    }
}