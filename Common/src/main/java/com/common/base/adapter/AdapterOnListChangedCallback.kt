package com.common.base.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.common.utils.LogUtils

/**
 * by y on 02/11/2017.
 */

class AdapterOnListChangedCallback<T> : ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {

    override fun onItemRangeMoved(p0: ObservableArrayList<T>, p1: Int, p2: Int, p3: Int) {
        LogUtils.i("onItemRangeMoved", (p0 + "  " + p1 + "  " + p2 + "  " + p3).toString())
    }

    override fun onItemRangeInserted(p0: ObservableArrayList<T>, p1: Int, p2: Int) {
        LogUtils.i("onItemRangeInserted", (p0 + "  " + p1 + "  " + p2).toString())
    }

    override fun onItemRangeChanged(p0: ObservableArrayList<T>, p1: Int, p2: Int) {
        LogUtils.i("onItemRangeChanged", (p0 + "  " + p1 + "  " + p2).toString())
    }

    override fun onChanged(p0: ObservableArrayList<T>) {
        LogUtils.i("onChanged", (p0).toString())
    }

    override fun onItemRangeRemoved(p0: ObservableArrayList<T>, p1: Int, p2: Int) {
        LogUtils.i("onItemRangeRemoved", (p0 + "  " + p1 + "  " + p2).toString())
    }
}
