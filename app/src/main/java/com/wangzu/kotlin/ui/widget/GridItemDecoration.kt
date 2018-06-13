package com.wangzu.kotlin.ui.widget

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by AnJiuZhe on 2018/6/6 0006.
 * Email 1573335865@qq.com
 */
class GridItemDecoration(spanCount: Int) : RecyclerView.ItemDecoration() {
    var spanCount: Int = 3

    init {
        this.spanCount = spanCount
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect?.left = 6
        outRect?.top = 6
        val index = parent?.getChildLayoutPosition(view)?.plus(1)
        if (index?.rem(spanCount) == 0) {
            outRect?.right = 6
        }
    }
}