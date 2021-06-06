package com.burkclik.videogamesapp.domain

import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.common.map
import com.burkclik.videogamesapp.data.GameRepository
import com.burkclik.videogamesapp.domain.model.GameDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GameDetailUseCase @Inject constructor(
    private val repository: GameRepository,
    private val gameDetailMapper: GameDetailMapper,
) {
    fun fetchDetailGame(movieId: Int): Flow<Resource<GameDetail>> {
        return repository
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
}