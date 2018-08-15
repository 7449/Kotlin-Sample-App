package com.common.base.adapter

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * by y on 02/11/2017.
 */
open class DataBindingAdapter<T, BIND : ViewDataBinding> : RecyclerView.Adapter<DataBindingHolder>() {

    private var mData: ObservableArrayList<T> = ObservableArrayList()
    private lateinit var viewDataBinding: BIND
    private var layoutId: Int = View.NO_ID
    private var bind: OnBind<T, BIND>? = null
    private var onClickListener: OnItemClickListener<T>? = null
    private var onLongClickListener: OnItemLongClickListener<T>? = null
    private var listChangedCallback: AdapterOnListChangedCallback<T, BIND> = AdapterOnListChangedCallback(this)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingHolder {
        viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        val viewHolder = DataBindingHolder(viewDataBinding)
        viewHolder.itemView.setOnClickListener { view ->
            onClickListener?.onItemClick(view, viewHolder.layoutPosition, mData[viewHolder.layoutPosition])
        }
        viewHolder.itemView.setOnLongClickListener { view ->
            onLongClickListener?.onItemLongClick(view, viewHolder.layoutPosition, mData[viewHolder.layoutPosition])
            true
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DataBindingHolder, position: Int) {
        bind?.onBind(DataBindingUtil.bind(holder.itemView)!!, position, mData[position])
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
        return this
    }

    fun add(data: T): DataBindingAdapter<T, BIND> {
        this.mData.add(data)
        return this
    }

    fun removeAll(): DataBindingAdapter<T, BIND> {
        this.mData.clear()
        return this
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mData.addOnListChangedCallback(listChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mData.removeOnListChangedCallback(listChangedCallback)
    }

    fun onChanged(sender: ObservableArrayList<T>) {
        resetData(sender)
        notifyDataSetChanged()
    }

    fun onItemRangeChanged(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        resetData(sender)
        notifyItemRangeChanged(positionStart, itemCount)
    }

    fun onItemRangeInserted(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        resetData(sender)
        notifyItemRangeInserted(positionStart, itemCount)
    }

    fun onItemRangeMoved(sender: ObservableArrayList<T>) {
        resetData(sender)
        notifyDataSetChanged()
    }

    fun onItemRangeRemoved(sender: ObservableArrayList<T>, positionStart: Int, itemCount: Int) {
        resetData(sender)
        notifyItemRangeRemoved(positionStart, itemCount)
    }

    private fun resetData(newItems: ObservableArrayList<T>) {
        this.mData = newItems
    }
}