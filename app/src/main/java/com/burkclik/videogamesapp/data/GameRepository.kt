package com.burkclik.videogamesapp.data

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.data.remote.GameDataSource
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepository @Inject constructor(private val gameDataSource: GameDataSource) {
    fun fetchGames(): Flow<Resource<GamesResponse>> = flow {
        emit(gameDataSource.getGames())
    }.map {
        Resource.Success(it)
    }

    fun fetchGameDetail(gameId: Int): Flow<Resource<GameDetailResponse>> = flow {
        emit(gameDataSource.getGameDetail(gameId))
    }.map {
        Resource.Success(it)
    }
}