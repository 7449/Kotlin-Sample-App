package com.codekk.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codekk.R
import com.codekk.databinding.FragmentCodekkOpListBinding
import com.codekk.databinding.ItemCodekkOpListBinding
import com.codekk.model.CodekkOpListModel
import com.codekk.viewmodel.CodekkOpListViewModel
import com.common.base.BaseEntity
import com.common.base.BaseFragment
import com.common.base.adapter.DataBindingAdapter
import com.common.base.adapter.OnBind
import com.common.base.adapter.OnItemClickListener
import com.common.utils.UIUtils
import com.common.widget.LoadMoreRecyclerView
import com.status.layout.Status

/**
 * @author y
 */
class CodekkOpListFragment : BaseFragment<FragmentCodekkOpListBinding>(),
        OnItemClickListener<CodekkOpListModel.ProjectArrayBean>,
        LoadMoreRecyclerView.LoadMoreListener,
        SwipeRefreshLayout.OnRefreshListener,
        OnBind<CodekkOpListModel.ProjectArrayBean, ItemCodekkOpListBinding>,
        Observer<BaseEntity<ObservableArrayList<CodekkOpListModel.ProjectArrayBean>>> {


    private lateinit var mAdapter: DataBindingAdapter<CodekkOpListModel.ProjectArrayBean, ItemCodekkOpListBinding>
    private lateinit var viewModel: CodekkOpListViewModel

    override fun initCreateView(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(CodekkOpListViewModel::class.java)
        binding.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = DataBindingAdapter<CodekkOpListModel.ProjectArrayBean, ItemCodekkOpListBinding>()
                .initLayoutId(R.layout.item_codekk_op_list)
                .setOnItemClickListener(this)
                .onBind(this)
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
    override fun onChanged(opList: BaseEntity<ObservableArrayList<CodekkOpListModel.ProjectArrayBean>>?) {
        if (opList == null) {
            onChangeStatusLayout(Status.ERROR)
            return
        }
        when (opList.type) {
            BaseEntity.EMPTY -> onChangeStatusLayout(Status.EMPTY)
            BaseEntity.REFRESH_ERROR -> binding.codekkRefreshLayout.isRefreshing = false
            BaseEntity.REFRESH -> binding.codekkRefreshLayout.isRefreshing = true
            BaseEntity.ERROR -> {
                if (opList.page == 1) {
                    onChangeStatusLayout(Status.ERROR)
                } else {
                    //
                }
            }
            BaseEntity.NOMORE -> UIUtils.toast(R.string.codekk_no_more)
            BaseEntity.SUCCESS -> {
                if (opList.page == 1) {
                    mAdapter.removeAll()
                }
                onChangeStatusLayout(Status.SUCCESS)
                binding.codekkRefreshLayout.isRefreshing = false
                mAdapter.addAll(opList.data!!)
            }
        }
    }

    override fun onItemClick(view: View, position: Int, info: CodekkOpListModel.ProjectArrayBean) {
    }

    override fun onBind(bind: ItemCodekkOpListBinding, position: Int, info: CodekkOpListModel.ProjectArrayBean) {
        bind.entity = info
    }

    override fun getLayoutId(): Int = R.layout.fragment_codekk_op_list
}
