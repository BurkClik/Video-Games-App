package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.local.model.GameEntity
import com.burkclik.videogamesapp.domain.model.Game
import javax.inject.Inject

class GameEntityToGamesMapper @Inject constructor() : Mapper<GameEntity, Game> {
    override fun mapFrom(type: GameEntity): Game {
        return Game(
            id = type.id,
            favorite = type.favorite,
            name = type.name,
            released = type.released,
            backgroundImage = type.backgroundImage,
            rating = type.rating.toString(),
        )
    }
}