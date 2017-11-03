package com.common.base.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import com.common.utils.LogUtils

/**
 * by y on 02/11/2017.
 *
 * 3.0源码暂时下载不下来
 *
 * 官方文档地址:
 * https://developer.android.com/reference/android/databinding/ObservableList.OnListChangedCallback.html#ObservableList.OnListChangedCallback()
 *
 */

class AdapterOnListChangedCallback<T, BIND : ViewDataBinding>(val adapter: DataBindingAdapter<T, BIND>) : ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {

    /****** 每当调用未知类型的变化已经发生，如整个列表被设为新值  *******/
    override fun onItemRangeMoved(sender: ObservableArrayList<T>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        LogUtils.i("onItemRangeMoved", (sender + "  " + fromPosition + "  " + toPosition + "  " + itemCount).toString())
    }

    /****** 每当项目已插入到列表中调用                        *******/
    override fun onItemRangeInserted(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        adapter.resetData(sender)
        LogUtils.i("onItemRangeInserted", (sender + "  " + positionStart + "  " + itemCount).toString())
    }

    /****** 每当调用列表中的一个或多个项目已经改变              *******/
    override fun onItemRangeChanged(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        LogUtils.i("onItemRangeChanged", (sender + "  " + positionStart + "  " + itemCount).toString())
    }

    /****** 每当调用未知类型的变化已经发生，如整个列表被设为新值  *******/
    override fun onChanged(sender: ObservableArrayList<T>) {
        LogUtils.i("onChanged", (sender).toString())
    }

    /****** 每当列表中的项目已被删除时调用                     *******/
    override fun onItemRangeRemoved(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
//        adapter.notifyItemRangeRemoved(positionStart, itemCount)
        LogUtils.i("onItemRangeRemoved", (sender + "  " + positionStart + "  " + itemCount).toString())
    }
}
