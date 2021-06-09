package com.burkclik.videogamesapp.data.remote.api

import com.burkclik.videogamesapp.common.API_KEY
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/games")
    suspend fun fetchGames(@Query("key") key: String = API_KEY): GamesResponse

    @GET("/games/{id}")
    suspend fun fetchGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY
    ): GameDetailResponse
}