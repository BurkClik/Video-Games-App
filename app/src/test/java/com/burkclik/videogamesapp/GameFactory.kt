package com.burkclik.videogamesapp

import com.burkclik.videogamesapp.domain.model.Game

object GameFactory {
    fun createGame(
        id: Int = 0,
        name: String = "",
        released: String = "",
        backgroundImage: String = "",
        rating: String = "",
    ): Game {
        return Game(
            id = id,
            name = name,
            released = released,
            backgroundImage = backgroundImage,
            rating = rating
        )
    }
}