package com.burkclik.videogamesapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class GameDetailResponse(
    val id: Int,
    val name: String,
    @SerializedName("description_raw") val description: String,
    val metacritic: Int,
    val released: String,
    @SerializedName("background_image") val backgroundImage: String,
    val rating: Double,
)