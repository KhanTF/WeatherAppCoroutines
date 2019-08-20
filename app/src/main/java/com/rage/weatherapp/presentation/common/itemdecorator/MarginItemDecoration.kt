package com.rage.weatherapp.presentation.common.itemdecorator

import android.content.Context
import android.graphics.Rect
import android.util.SparseArray
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    context: Context,
    @DimenRes private val vertical: Int = 0,
    @DimenRes private val horizontal: Int = 0,
    @DimenRes private val space: Int = 0
) : RecyclerView.ItemDecoration() {

    private val verticalSize = if (vertical == 0) context.resources.getDimensionPixelSize(vertical) else 0
    private val horizontalSize = if (horizontal == 0) context.resources.getDimensionPixelSize(horizontal) else 0
    private val spaceSize = if (space == 0) context.resources.getDimensionPixelSize(space) else 0

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val viewHolder = parent.getChildViewHolder(view)
        val position = viewHolder.adapterPosition
        val count = parent.adapter?.itemCount ?: 0
        outRect.left = horizontalSize
        outRect.right = horizontalSize
        outRect.top = verticalSize
        if (position == count - 1) {
            outRect.bottom = verticalSize
        } else {
            outRect.bottom = spaceSize
        }
    }

}