package com.gankkotlin.extension

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * 描    述：
 * 作    者：xul@13322.com
 * 时    间：2017/6/29
 */

class RecyclerViewDivider(val dividerDrawable: Drawable, val leftPadding: Int = 0, val rightPadding: Int = 0, val height: Int = dividerDrawable.intrinsicHeight) : RecyclerView.ItemDecoration() {
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        drawVertical(c, parent, leftPadding, rightPadding)
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter.itemCount - 1)
            outRect.set(0, 0, 0, height)
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView, leftPadding: Int, rightPadding: Int) {
        val childCount = parent.childCount
        for (i in 0..childCount - 1) {
            val left: Int
            val right: Int
            if (i == childCount - 1) {
                left = parent.paddingLeft
                right = parent.width - parent.paddingRight
            } else {
                left = parent.paddingLeft + leftPadding
                right = parent.width - parent.paddingRight - rightPadding
            }
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            val top: Int
            val bottom: Int
            if (i == childCount - 1) {
                bottom = child.top - params.topMargin
                top = bottom
            } else {
                top = child.bottom + params.bottomMargin
                bottom = top + height
            }
            dividerDrawable.setBounds(left, top, right, bottom)
            dividerDrawable.draw(canvas)

        }
    }
}
