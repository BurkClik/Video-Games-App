package com.burkclik.videogamesapp.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.videogamesapp.R

class GamesAdapterDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = view.context.resources.getDimensionPixelOffset(R.dimen.games_vertical_spacing)
        outRect.bottom = spacing
        outRect.top = spacing
    }
}