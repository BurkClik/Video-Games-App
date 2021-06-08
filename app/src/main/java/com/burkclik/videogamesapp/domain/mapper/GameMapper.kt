package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import com.burkclik.videogamesapp.domain.model.Game
import javax.inject.Inject

class GameMapper @Inject constructor() : Mapper<GamesResponse, List<Game>> {
    override fun mapFrom(type: GamesResponse): List<Game> {
        return type.results.map { game ->
            Game(
                id = game.id,
                name = game.name,
                released = game.released,
                backgroundImage = game.backgroundImage,
                rating = game.rating.toString(),
                favorite = false
            )
        }
    }
}