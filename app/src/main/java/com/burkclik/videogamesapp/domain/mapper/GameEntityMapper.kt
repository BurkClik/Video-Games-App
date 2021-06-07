package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.local.model.GameEntity
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import javax.inject.Inject

class GameEntityMapper @Inject constructor() : Mapper<GamesResponse, List<GameEntity>> {
    override fun mapFrom(type: GamesResponse): List<GameEntity> {
        return type.results.map { game ->
            GameEntity(
                id = game.id,
                name = game.name,
                released = game.released,
                backgroundImage = game.backgroundImage,
                rating = game.rating,
            )
        }
    }
}