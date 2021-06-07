package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.local.model.GameEntity
import com.burkclik.videogamesapp.domain.model.Games
import javax.inject.Inject

class GameEntityToGamesMapper @Inject constructor() : Mapper<List<GameEntity>, List<Games>> {
    override fun mapFrom(type: List<GameEntity>): List<Games> {
        return type.map {
            Games(
                id = it.id,
                name = it.name,
                released = it.released,
                backgroundImage = it.backgroundImage,
                rating = it.rating.toString(),
            )
        }
    }
}