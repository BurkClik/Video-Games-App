package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.common.Mapper
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import com.burkclik.videogamesapp.domain.model.GameDetail
import javax.inject.Inject

class GameDetailMapper @Inject constructor() : Mapper<GameDetailResponse, GameDetail> {
    override fun mapFrom(type: GameDetailResponse): GameDetail {
        return GameDetail(
            id = type.id,
            name = type.name,
            description = type.description,
            metacritic = type.metacritic.toString(),
            released = type.released,
            backgroundImage = type.backgroundImage,
            rating = type.rating.toString(),
        )
    }
}