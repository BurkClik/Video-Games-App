package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import com.burkclik.videogamesapp.domain.model.Games
import javax.inject.Inject

class GameMapper @Inject constructor() : Mapper<GamesResponse, List<Games>> {
    override fun mapFrom(type: GamesResponse): List<Games> {
        return type.results.map { game ->
            Games(
                id = game.id,
                name = game.name,
                released = game.released,
                backgroundImage = game.backgroundImage,
                rating = game.rating.toString(),
            )
        }
    }
}