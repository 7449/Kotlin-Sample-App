package com.common.base.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.common.utils.LogUtils


/**
 * by y on 02/11/2017.
 */
class DataBindingAdapter<T, BIND : ViewDataBinding> : RecyclerView.Adapter<DataBindingHolder>() {

    private var mData: ObservableArrayList<T> = ObservableArrayList()
    private lateinit var viewDataBinding: BIND
    private var layoutId: Int = -1
    private var bind: OnBind<T, BIND>? = null
    private var onClickListener: OnItemClickListener<T>? = null
    private var onLongClickListener: OnItemLongClickListener<T>? = null
    private var listChangedCallback: AdapterOnListChangedCallback<T, BIND> = AdapterOnListChangedCallback(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingHolder {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return DataBindingHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: DataBindingHolder, position: Int) {
        if (bind != null) {
            bind?.onBind(DataBindingUtil.bind(holder.itemView), position, mData[position])
        } else {
            LogUtils.w("bind listener == null")
        }
        if (onClickListener != null) {
            onClickListener?.onItemClick(holder.itemView, position, mData[position])
        } else {
            LogUtils.w("onClickListener == null")
        }
        if (onLongClickListener != null) {
            onLongClickListener?.onItemLongClick(holder.itemView, position, mData[position])
        } else {
            LogUtils.w("onLongClickListener == null")
        }
    }

    override fun getItemCount(): Int = mData.size

    fun initLayoutId(@LayoutRes id: Int): DataBindingAdapter<T, BIND> {
        this.layoutId = id
        return this
    }

    fun onBind(bind: OnBind<T, BIND>): DataBindingAdapter<T, BIND> {
        this.bind = bind
        return this
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>): DataBindingAdapter<T, BIND> {
        this.onClickListener = listener
        return this
    }

    fun setOnLongItemClickListener(listener: OnItemLongClickListener<T>): DataBindingAdapter<T, BIND> {
        this.onLongClickListener = listener
        return this
    }

    fun addAll(data: ObservableArrayList<T>): DataBindingAdapter<T, BIND> {
        this.mData.addAll(data)
        notifyDataSetChanged()
        return this
    }

    fun resetData(data: ObservableArrayList<T>): DataBindingAdapter<T, BIND> {
        this.mData = data
        notifyDataSetChanged()
        return this
    }

    fun add(data: T): DataBindingAdapter<T, BIND> {
        this.mData.add(data)
        notifyDataSetChanged()
        return this
    }

    fun removeAll(): DataBindingAdapter<T, BIND> {
        this.mData.clear()
        notifyDataSetChanged()
        return this
    }

    fun remove(data: T): DataBindingAdapter<T, BIND> {
        this.mData.remove(data)
        notifyDataSetChanged()
        return this
    }

    fun remove(position: Int): DataBindingAdapter<T, BIND> {
        this.mData.remove(mData[position])
        notifyDataSetChanged()
        return this
    }

    fun getData(position: Int): T {
        return mData[position]
    }

    fun getData(): ObservableArrayList<T> {
        return mData
    }

    fun onDestroy() {
        LogUtils.i("onDestroy-->removeOnListChangedCallback")
        mData.removeOnListChangedCallback(listChangedCallback)
        viewDataBinding.unbind()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        LogUtils.i("addOnListChangedCallback")
        mData.addOnListChangedCallback(listChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        super.onDetachedFromRecyclerView(recyclerView)
        LogUtils.i("removeOnListChangedCallback")
//        mData.removeOnListChangedCallback(listChangedCallback)
    }
}