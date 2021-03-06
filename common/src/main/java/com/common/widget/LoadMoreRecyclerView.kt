package com.common.widget

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * by y on 27/09/2017.
 */
class LoadMoreRecyclerView : RecyclerView {
    private var layoutManagerType: LAYOUT? = null
    private var lastPositions: IntArray? = null
    private var lastVisibleItemPosition: Int = 0
    private var loadingData: LoadMoreListener? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    constructor(context: Context) : super(context)


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setLoadingMore(loadingData: LoadMoreListener) {
        this.loadingData = loadingData
    }

    fun setRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)

        val layoutManager = layoutManager
        if (layoutManagerType == null) {
            layoutManagerType = when (layoutManager) {
                is GridLayoutManager -> LAYOUT.GRID
                is LinearLayoutManager -> LAYOUT.LINEAR
                is StaggeredGridLayoutManager -> LAYOUT.STAGGERED_GRID
                else -> throw RuntimeException(
                        "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager")
            }
        }

        when (layoutManagerType) {
            LAYOUT.LINEAR -> lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            LAYOUT.GRID -> lastVisibleItemPosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            LAYOUT.STAGGERED_GRID -> {
                val staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager
                if (lastPositions == null) {
                    lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                }
                staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                lastVisibleItemPosition = findMax(lastPositions!!)
            }
        }

    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        val layoutManager = layoutManager
        val visibleItemCount = layoutManager?.childCount ?: 0
        val totalItemCount = layoutManager?.itemCount ?: 0
        if (visibleItemCount > 0 && state == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItemPosition == totalItemCount - 1 && swipeRefreshLayout != null && !swipeRefreshLayout?.isRefreshing!!) {
            loadingData?.onLoadMore()
        }
    }

    private fun findMax(lastPositions: IntArray): Int = lastPositions.max() ?: lastPositions[0]

    private enum class LAYOUT {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }

}