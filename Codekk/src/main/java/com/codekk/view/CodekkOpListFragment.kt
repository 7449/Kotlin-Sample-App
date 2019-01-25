package com.codekk.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codekk.BR
import com.codekk.R
import com.codekk.databinding.FragmentCodekkOpListBinding
import com.codekk.model.CodekkOpListModel
import com.codekk.viewmodel.CodekkOpListViewModel
import com.common.base.*
import com.common.utils.toast
import com.common.widget.LoadMoreRecyclerView
import com.status.layout.StatusLayout
import com.xadapter.OnItemClickListener
import com.xadapter.adapter.XDataBindingAdapter
import com.xadapter.adapter.XDataBindingAdapterFactory
import io.reactivex.network.RxNetWork

/**
 * @author y
 */
class CodekkOpListFragment : BaseFragment<FragmentCodekkOpListBinding>(),
        OnItemClickListener<CodekkOpListModel.ProjectArrayBean>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener,
        Observer<BaseEntity<ObservableArrayList<CodekkOpListModel.ProjectArrayBean>>> {


    private lateinit var mAdapter: XDataBindingAdapter<CodekkOpListModel.ProjectArrayBean>
    private lateinit var viewModel: CodekkOpListViewModel

    override fun initCreateView(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CodekkOpListViewModel::class.java)
        binding.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = XDataBindingAdapterFactory(BR.entity)
        mAdapter.apply {
            itemLayoutId = R.layout.item_codekk_op_list
            onItemClickListener = this@CodekkOpListFragment
        }
        binding.codekkRecyclerView.setHasFixedSize(true)
        binding.codekkRecyclerView.setLoadingMore(this)
        binding.codekkRecyclerView.setRefreshLayout(binding.codekkRefreshLayout)
        binding.codekkRecyclerView.adapter = mAdapter
        binding.codekkRefreshLayout.setOnRefreshListener(this)
        binding.codekkRefreshLayout.post { onRefresh() }
        viewModel.opList.observe(this, this)
    }

    override fun onRefresh() = viewModel.onRefresh()

    override fun onLoadMore() = viewModel.onLoadMore()

    override fun onChanged(opList: BaseEntity<ObservableArrayList<CodekkOpListModel.ProjectArrayBean>>) {
        when (opList.type) {
            ENTITY_EMPTY -> {
                onChangeStatusLayout(StatusLayout.EMPTY)
            }
            ENTITY_REFRESH_ERROR -> {
                binding.codekkRefreshLayout.isRefreshing = false
            }
            ENTITY_REFRESH -> {
                binding.codekkRefreshLayout.isRefreshing = true
            }
            ENTITY_ERROR -> {
                if (opList.page == 1) {
                    onChangeStatusLayout(StatusLayout.ERROR)
                }
            }
            ENTITY_NOMORE -> {
                toast(R.string.codekk_no_more)
            }
            ENTITY_SUCCESS -> {
                if (opList.page == 1) {
                    mAdapter.removeAll()
                }
                onChangeStatusLayout(StatusLayout.SUCCESS)
                binding.codekkRefreshLayout.isRefreshing = false
                mAdapter.addAll(opList.data!!)
            }
        }
    }

    override fun onItemClick(view: View, position: Int, entity: CodekkOpListModel.ProjectArrayBean) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RxNetWork.instance.cancel(CodekkOpListViewModel::class.java.simpleName)
    }

    override val layoutId: Int = R.layout.fragment_codekk_op_list
}
