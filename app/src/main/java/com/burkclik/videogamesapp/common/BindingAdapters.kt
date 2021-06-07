package com.burkclik.videogamesapp.common

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.burkclik.videogamesapp.R

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

@BindingAdapter("app:favoriteIcon")
fun setFavoriteIcon(image: ImageView, state: Boolean) {
    if (!state) {
        image.setImageResource(R.drawable.ic_favorite)
    } else {
        image.setImageResource(R.drawable.ic_favorite_true)
    }
}