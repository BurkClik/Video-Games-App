package com.burkclik.videogamesapp.data.remote

import com.burkclik.videogamesapp.data.remote.api.ApiService
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import javax.inject.Inject

class GameDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getGames(): GamesResponse = apiService.fetchGames()
    suspend fun getGameDetail(gameId: Int): GameDetailResponse = apiService.fetchGamesDetail(gameId)
}