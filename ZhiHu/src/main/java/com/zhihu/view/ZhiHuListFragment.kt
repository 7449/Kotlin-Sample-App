package com.zhihu.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.common.base.*
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.utils.openActivity
import com.status.layout.ERROR
import com.status.layout.LOADING
import com.status.layout.SUCCESS
import com.zhihu.R
import com.zhihu.ZhiHuConstant
import com.zhihu.databinding.FragmentZhihuListBinding
import com.zhihu.databinding.ItemZhihuListBinding
import com.zhihu.model.ZhiHuListModel
import com.zhihu.viewmodel.ZhiHuListViewModel
import io.reactivex.network.RxNetWork

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
        viewModel = ViewModelProviders.of(this).get(ZhiHuListViewModel::class.java)
        binding.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = DataBindingAdapter<ZhiHuListModel, ItemZhihuListBinding>()
                .initLayoutId(R.layout.item_zhihu_list)
                .setOnItemClickListener(this)
                .onBind(this)
        binding.zhihuRecyclerView.setHasFixedSize(true)
        binding.zhihuRecyclerView.adapter = mAdapter
        viewModel.request(ZhiHuConstant.getSuffix(bundle?.getInt(ZhiHuConstant.FRAGMENT_INDEX)
                ?: 0, bundle?.getString(ZhiHuConstant.FRAGMENT_TYPE) ?: ""))
                .viewModelData.observe(this, this)
    }

    override fun onItemClick(view: View, position: Int, info: ZhiHuListModel) {
        val bundle = Bundle()
        bundle.putInt(ZHIHU_DETAIL_SLUG, info.slug)
        openActivity(ZhiHuDetailActivity().javaClass, bundle)
    }

    override fun onBind(bind: ItemZhihuListBinding, position: Int, info: ZhiHuListModel) {
        bind.entity = info
    }

    override fun onChanged(zhihuList: BaseEntity<ObservableArrayList<ZhiHuListModel>>) {
        when (zhihuList.type) {
            ENTITY_ERROR -> {
                onChangeStatusLayout(ERROR)
            }
            ENTITY_LOADING -> {
                onChangeStatusLayout(LOADING)
            }
            ENTITY_SUCCESS -> {
                onChangeStatusLayout(SUCCESS)
                mAdapter.addAll(zhihuList.data ?: ObservableArrayList())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RxNetWork.instance.cancel(ZhiHuListViewModel::class.java.simpleName)
    }

    override fun getLayoutId(): Int = R.layout.fragment_zhihu_list

}
