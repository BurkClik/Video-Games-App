package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.data.FavoriteRepository
import com.burkclik.videogamesapp.domain.mapper.GameEntityToGamesMapper
import com.burkclik.videogamesapp.domain.model.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val gamesEntityToGamesMapper: GameEntityToGamesMapper,
) {
    fun getFavorites(): Flow<List<Games>> =
        favoriteRepository
            .fetchFavorites()
            .map {
                gamesEntityToGamesMapper.mapFrom(it)
            }
            .flowOn(Dispatchers.Default)
}