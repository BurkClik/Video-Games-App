package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.common.map
import com.burkclik.videogamesapp.data.GameDetailRepository
import com.burkclik.videogamesapp.domain.mapper.GameDetailMapper
import com.burkclik.videogamesapp.domain.mapper.GameEntityToGamesMapper
import com.burkclik.videogamesapp.domain.model.GameDetail
import com.burkclik.videogamesapp.domain.model.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(
    private val gameDetailRepository: GameDetailRepository,
    private val gameDetailMapper: GameDetailMapper,
    private val gameEntityToGamesMapperMapper: GameEntityToGamesMapper,
) {
    fun fetchDetailGame(movieId: Int): Flow<Resource<GameDetail>> {
        return gameDetailRepository
            .fetchGameDetail(movieId)
            .map { resource ->
                resource.map {
                    gameDetailMapper.mapFrom(it)
                }
            }
            .onStart { emit(Resource.Loading) }
            .catch { emit(Resource.Error(it)) }
            .flowOn(Dispatchers.Default)
    }

    fun getGameById(gameId: Int): Flow<Games> = gameDetailRepository
        .getGameById(gameId)
        .map {
            gameEntityToGamesMapperMapper.mapFrom(it)
        }
        .flowOn(Dispatchers.Default)

    suspend fun updateFavorite(state: Boolean, id: Int) {
        gameDetailRepository.updateFavorite(state, id)
    }
}