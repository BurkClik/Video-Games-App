package com.burkclik.videogamesapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val id: Int,
    val name: String,
    val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    val rating: Double,
)
