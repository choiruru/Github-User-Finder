package com.choimuhtadin.githubuserfinder.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnScrollListener(private val layoutManager: LinearLayoutManager, val onLoadMoreListener: () -> Unit) : RecyclerView.OnScrollListener() {
    var previousTotal = 0
    var loading = true
    val visibleThreshold = 3
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loading = true
            onLoadMoreListener.invoke()
        }
    }
}