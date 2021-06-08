package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.common.map
import com.burkclik.videogamesapp.data.GameRepository
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import com.burkclik.videogamesapp.domain.mapper.GameEntityMapper
import com.burkclik.videogamesapp.domain.mapper.GameEntityToGamesMapper
import com.burkclik.videogamesapp.domain.mapper.GameMapper
import com.burkclik.videogamesapp.domain.model.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GameUseCase @Inject constructor(
    private val repository: GameRepository,
    private val gameMapper: GameMapper,
    private val gameEntityMapper: GameEntityMapper,
    private val gameEntityToGamesMapper: GameEntityToGamesMapper,
) {
    fun fetchGames(): Flow<Resource<List<Games>>> {
        return repository
            .fetchGames()
            .map { resource ->
                resource.map {
                    saveGames(it)
                    gameMapper.mapFrom(it)
                }
            }
            .onStart { emit(Resource.Loading) }
            .catch { emit(Resource.Error(it)) }
            .flowOn(Dispatchers.IO)
    }

    private suspend fun saveGames(games: GamesResponse) {
        repository
            .saveGames(gameEntityMapper.mapFrom(games))
    }

    fun searchGames(search: String?): Flow<List<Games>> {
        return repository
            .searchGame(search)
            .map {
                gameEntityToGamesMapper.mapFrom(it)
            }.flowOn(Dispatchers.IO)
    }
}