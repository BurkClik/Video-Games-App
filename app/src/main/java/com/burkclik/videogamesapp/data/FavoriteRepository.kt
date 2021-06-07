package com.burkclik.videogamesapp.data

import com.burkclik.videogamesapp.data.local.GameLocalDataSource
import com.burkclik.videogamesapp.data.local.model.GameEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val gameLocalDataSource: GameLocalDataSource) {
    fun fetchFavorites(): Flow<List<GameEntity>> = flow {
        emit(gameLocalDataSource.getFavorites())
    }
}