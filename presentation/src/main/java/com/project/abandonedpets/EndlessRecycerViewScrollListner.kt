package com.project.abandonedpets

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

abstract class EndlessRecycerViewScrollListenner : RecyclerView.OnScrollListener {
    // 로딩량
    private var visibleThreshold = 5

    // 현재 페이지
    private var currentPage = 0

    // 지금까지 총 아이템 수
    private var previousTotalItemCount = 0

    // 로딩 여부
    private var loading = true

    // 시작 페이지 인덱스
    private var startingPageIndex = 0
    private var mLayoutManager: RecyclerView.LayoutManager

    constructor(layoutManager: LinearLayoutManager) {
        mLayoutManager = layoutManager
    }

    constructor(layoutManager: GridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    constructor(layoutManager: StaggeredGridLayoutManager) {
        mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            }
            is LinearLayoutManager -> {
                lastVisibleItemPosition =
                    (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            }
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0)
                loading = true
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }

    // 새로고침
    fun resetState() {
        currentPage = startingPageIndex
        previousTotalItemCount = 0
        loading = true
    }

    abstract fun onLoadMore(page: Int, totalItemCount: Int, view: RecyclerView?)
}