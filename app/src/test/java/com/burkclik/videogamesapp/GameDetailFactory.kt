package com.burkclik.videogamesapp

import com.burkclik.videogamesapp.domain.model.GameDetail

object GameDetailFactory {

    fun createGameDetail(
        id: Int = 0,
        name: String = "",
        description: String = "",
        metacritic: String = "",
        released: String = "",
        backgroundImage: String = "",
        rating: String = "",
    ): GameDetail {
        return GameDetail(
            id = id,
            name = name,
            description = description,
            metacritic = metacritic,
            released = released,
            backgroundImage = backgroundImage,
            rating = rating
        )
    }
}