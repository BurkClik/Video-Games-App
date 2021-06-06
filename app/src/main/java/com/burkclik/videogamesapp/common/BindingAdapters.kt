package com.burkclik.videogamesapp.common

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this)
            .load(imageUrl)
            .into(this)
    }
}

@BindingAdapter("app:setVisibility")
fun setVisibility(view: View, demoText: String) {
    view.isVisible = demoText.length < 3
}