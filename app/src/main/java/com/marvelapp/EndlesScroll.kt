package com.marvelapp

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true
    private val startingPageIndex = 0

    private var layoutManager: RecyclerView.LayoutManager = layoutManager

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount

        when (layoutManager) {
            is LinearLayoutManager -> lastVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}