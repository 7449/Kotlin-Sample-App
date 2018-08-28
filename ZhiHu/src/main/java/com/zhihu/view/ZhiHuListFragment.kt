package com.zhihu.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.common.base.BaseEntity
import com.common.base.LazyFragment
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.utils.UIUtils
import com.status.layout.Status
import com.yyxk.xlog.XLog
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.FragmentZhihuListBinding
import com.zhihu.databinding.ItemZhihuListBinding
import com.zhihu.model.ZhiHuListModel
import com.zhihu.viewmodel.ZhiHuListViewModel

/**
 * @author y
 */
class ZhiHuListFragment : LazyFragment<FragmentZhihuListBinding>(),
        OnItemClickListener<ZhiHuListModel>,
        OnBind<ZhiHuListModel, ItemZhihuListBinding>,
        Observer<BaseEntity<ObservableArrayList<ZhiHuListModel>>> {


    companion object {
        fun newInstance(position: Int, type: String): ZhiHuListFragment {
            val listFragment = ZhiHuListFragment()
            val bundle = Bundle()
            bundle.putInt(ZhiHuConstant.FRAGMENT_INDEX, position)
            bundle.putString(ZhiHuConstant.FRAGMENT_TYPE, type)
            listFragment.arguments = bundle
            return listFragment
        }
    }

    private lateinit var mAdapter: DataBindingAdapter<ZhiHuListModel, ItemZhihuListBinding>
    private lateinit var viewModel: ZhiHuListViewModel

    override fun initCreateView(savedInstanceState: Bundle?) {
    }

    override fun initActivityCreated() {
        XLog.d("")
        viewModel = ViewModelProviders.of(this).get(ZhiHuListViewModel::class.java)
        binding.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = DataBindingAdapter<ZhiHuListModel, ItemZhihuListBinding>()
                .initLayoutId(R.layout.item_zhihu_list)
                .setOnItemClickListener(this)
                .onBind(this)
        binding.zhihuRecyclerView.setHasFixedSize(true)
        binding.zhihuRecyclerView.adapter = mAdapter
        viewModel.request(ZhiHuConstant.getSuffix(bundle!!.getInt(ZhiHuConstant.FRAGMENT_INDEX), bundle!!.getString(ZhiHuConstant.FRAGMENT_TYPE)))
                .viewModelData.observe(this, this)
    }

    override fun onItemClick(view: View, position: Int, info: ZhiHuListModel) {
        val bundle = Bundle()
        bundle.putInt(ZhiHuDetailActivity.SLUG, info.slug)
        UIUtils.startActivity(ZhiHuDetailActivity().javaClass, bundle)
    }

    override fun onBind(bind: ItemZhihuListBinding, position: Int, info: ZhiHuListModel) {
        bind.entity = info
    }

    override fun onChanged(zhihuList: BaseEntity<ObservableArrayList<ZhiHuListModel>>?) {
        if (zhihuList == null) {
            onChangeStatusLayout(Status.ERROR)
            return
        }
        when (zhihuList.type) {
            BaseEntity.ERROR -> onChangeStatusLayout(Status.ERROR)
            BaseEntity.LOADING -> onChangeStatusLayout(Status.LOADING)
            BaseEntity.SUCCESS -> {
                onChangeStatusLayout(Status.SUCCESS)
                mAdapter.addAll(zhihuList.data!!)
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_zhihu_list

}
