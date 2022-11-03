package com.example.memefon.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridSpacingItemDecoration(
    private val topSpacing: Int,
    private val leftSpacing: Int,
    private val rightSpacing: Int,
    private val bottomSpacing: Int,
    private var displayMode: Int
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        val layoutManager = parent.layoutManager
        setSpacingForDirection(outRect, layoutManager, position, itemCount)
    }

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    ) {

        // Resolve display mode automatically
        if (displayMode == -1) {
            displayMode = resolveDisplayMode(layoutManager)
        }
        when (displayMode) {
            HORIZONTAL -> {
                outRect.left = leftSpacing
                outRect.right = if (position == itemCount - 1) rightSpacing else 0
                outRect.top = topSpacing
                outRect.bottom = bottomSpacing
            }
            VERTICAL -> {
                outRect.left = leftSpacing
                outRect.right = rightSpacing
                outRect.top = topSpacing
                outRect.bottom = if (position == itemCount - 1) bottomSpacing else 0
            }
            GRID -> if (layoutManager is GridLayoutManager) {
                val cols = layoutManager.spanCount
                val rows: Int = if (itemCount % cols == 0) {
                    itemCount / cols
                } else {
                    itemCount / cols + 1
                }
                outRect.left = leftSpacing
                outRect.right = if (position % cols == cols - 1) rightSpacing else 0
                outRect.top = topSpacing
                outRect.bottom = if (position / cols == rows - 1) bottomSpacing else 0
            }
        }
    }

    private fun resolveDisplayMode(layoutManager: RecyclerView.LayoutManager?): Int {
        if (layoutManager is GridLayoutManager) return GRID
        return if (layoutManager!!.canScrollHorizontally()) HORIZONTAL else VERTICAL
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}