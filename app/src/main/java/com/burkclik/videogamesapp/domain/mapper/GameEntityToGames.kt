package com.burkclik.videogamesapp.domain.mapper

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.local.model.GameEntity
import com.burkclik.videogamesapp.domain.model.Games
import javax.inject.Inject

class GameEntityToGames @Inject constructor() : Mapper<GameEntity, Games> {
    override fun mapFrom(type: GameEntity): Games {
        return Games(
            id = type.id,
            favorite = type.favorite,
            name = type.name,
            released = type.released,
            backgroundImage = type.backgroundImage,
            rating = type.rating.toString(),
        )
    }
}