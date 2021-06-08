package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.data.FavoriteRepository
import com.burkclik.videogamesapp.domain.mapper.GameEntityToGamesMapper
import com.burkclik.videogamesapp.domain.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val gameEntityToGamesMapper: GameEntityToGamesMapper,
) {
    fun getFavorites(): Flow<List<Game>> =
        favoriteRepository
            .fetchFavorites()
            .map {
                it.map { gameEntity ->
                    gameEntityToGamesMapper.mapFrom(gameEntity)
                }
            }
            .flowOn(Dispatchers.Default)
}