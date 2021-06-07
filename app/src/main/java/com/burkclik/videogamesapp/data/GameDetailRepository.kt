package com.burkclik.videogamesapp.data

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.data.local.GameLocalDataSource
import com.burkclik.videogamesapp.data.remote.GameDataSource
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameDetailRepository @Inject constructor(
    private val gameLocalDataSource: GameLocalDataSource,
    private val gameDataSource: GameDataSource,
) {
    suspend fun updateFavorite(state: Boolean, id: Int) = gameLocalDataSource.addFavorite(state, id)

    fun fetchGameDetail(gameId: Int): Flow<Resource<GameDetailResponse>> = flow {
        emit(gameDataSource.getGameDetail(gameId))
    }.map {
        Resource.Success(it)
    }
}