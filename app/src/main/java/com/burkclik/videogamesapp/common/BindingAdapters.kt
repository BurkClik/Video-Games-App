package com.burkclik.videogamesapp.common

import android.widget.ImageView
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