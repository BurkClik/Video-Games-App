package com.burkclik.videogamesapp.data

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.data.local.GameLocalDataSource
import com.burkclik.videogamesapp.data.local.model.GameEntity
import com.burkclik.videogamesapp.data.remote.GameDataSource
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gameDataSource: GameDataSource,
    private val gameLocalDataSource: GameLocalDataSource,
) {
    fun fetchGames(): Flow<Resource<GamesResponse>> = flow {
        emit(gameDataSource.getGames())
    }.map {
        Resource.Success(it)
    }

    fun fetchGamesLocal(): Flow<List<GameEntity>> = flow {
        emit(gameLocalDataSource.getGames())
    }

    fun searchGame(search: String?): Flow<List<GameEntity>> = flow {
        emit(gameLocalDataSource.searchGame(search))
    }

    suspend fun saveGames(games: List<GameEntity>) = gameLocalDataSource.insertGames(games)
}