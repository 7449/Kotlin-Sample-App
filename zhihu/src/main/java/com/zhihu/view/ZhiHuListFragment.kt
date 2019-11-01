package com.zhihu.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.common.base.*
import com.common.utils.openActivity
import com.status.layout.StatusLayout
import com.xadapter.OnItemClickListener
import com.xadapter.adapter.XDataBindingAdapter
import com.xadapter.adapter.XDataBindingAdapterFactory
import com.zhihu.BR
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.FragmentZhihuListBinding
import com.zhihu.model.ZhiHuListModel
import com.zhihu.viewmodel.ZhiHuListViewModel
import io.reactivex.network.RxNetWork

/**
 * @author y
 */
class ZhiHuListFragment : LazyFragment<FragmentZhihuListBinding>(),
        OnItemClickListener<ZhiHuListModel>,
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

    private lateinit var mAdapter: XDataBindingAdapter<ZhiHuListModel>
    private lateinit var viewModel: ZhiHuListViewModel

    override fun initCreateView(savedInstanceState: Bundle?) {
    }

    override fun initActivityCreated() {
        viewModel = ViewModelProviders.of(this).get(ZhiHuListViewModel::class.java)
        binding.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = XDataBindingAdapterFactory(BR.entity)
        mAdapter.apply {
            itemLayoutId = R.layout.item_zhihu_list
            onItemClickListener = this@ZhiHuListFragment
        }
        binding.zhihuRecyclerView.setHasFixedSize(true)
        binding.zhihuRecyclerView.adapter = mAdapter
        viewModel.request(ZhiHuConstant.getSuffix(bundle?.getInt(ZhiHuConstant.FRAGMENT_INDEX)
                ?: 0, bundle?.getString(ZhiHuConstant.FRAGMENT_TYPE) ?: ""))
                .viewModelData.observe(this, this)
    }

    override fun onItemClick(view: View, position: Int, entity: ZhiHuListModel) {
        val bundle = Bundle()
        bundle.putInt(ZHIHU_DETAIL_SLUG, entity.slug)
        openActivity(ZhiHuDetailActivity().javaClass, bundle)
    }

    override fun onChanged(zhihuList: BaseEntity<ObservableArrayList<ZhiHuListModel>>) {
        when (zhihuList.type) {
            ENTITY_ERROR -> {
                onChangeStatusLayout(StatusLayout.ERROR)
            }
            ENTITY_LOADING -> {
                onChangeStatusLayout(StatusLayout.LOADING)
            }
            ENTITY_SUCCESS -> {
                onChangeStatusLayout(StatusLayout.SUCCESS)
                mAdapter.addAll(zhihuList.data ?: ObservableArrayList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RxNetWork.instance.cancel(ZhiHuListViewModel::class.java.simpleName)
    }

    override val layoutId: Int = R.layout.fragment_zhihu_list

}
