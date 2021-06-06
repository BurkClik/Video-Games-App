package com.burkclik.videogamesapp.domain.model

data class GameDetail(
    val id: Int,
    val name: String,
    val description: String,
    val metacritic: String,
    val released: String,
    val backgroundImage: String,
    val rating: String,
)