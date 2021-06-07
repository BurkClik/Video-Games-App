package com.burkclik.videogamesapp.domain.model

import com.burkclik.videogamesapp.common.BindableItem

data class Games(
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: String,
) : BindableItem
