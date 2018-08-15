package com.common.base.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ViewDataBinding

/**
 * by y on 02/11/2017.
 */
class AdapterOnListChangedCallback<T, BIND : ViewDataBinding>(private val adapter: DataBindingAdapter<T, BIND>) :
        ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {

    /****** 每当调用未知类型的变化已经发生，如整个列表被设为新值  *******/
    override fun onItemRangeMoved(sender: ObservableArrayList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        adapter.onItemRangeMoved(sender)
    }

    /****** 每当项目已插入到列表中调用                        *******/
    override fun onItemRangeInserted(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        adapter.onItemRangeInserted(sender, positionStart, itemCount)
    }

    /****** 每当调用列表中的一个或多个项目已经改变              *******/
    override fun onItemRangeChanged(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        adapter.onItemRangeChanged(sender, positionStart, itemCount)
    }

    /****** 每当调用未知类型的变化已经发生，如整个列表被设为新值  *******/
    override fun onChanged(sender: ObservableArrayList<T>) {
        adapter.onChanged(sender)
    }

    /****** 每当列表中的项目已被删除时调用                     *******/
    override fun onItemRangeRemoved(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        adapter.onItemRangeRemoved(sender, positionStart, itemCount)
    }
}
