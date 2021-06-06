package com.burkclik.videogamesapp.data.remote.api

import com.burkclik.videogamesapp.common.API_KEY
import com.burkclik.videogamesapp.data.remote.model.GameDetailResponse
import com.burkclik.videogamesapp.data.remote.model.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("x-rapidapi-key: 49ea5b81a3msh2b2702e3de3f01fp1b7ed0jsn9ce708189264")
    @GET("/games")
    suspend fun fetchGames(@Query("key") key: String = API_KEY): GamesResponse

    @Headers("x-rapidapi-key: 49ea5b81a3msh2b2702e3de3f01fp1b7ed0jsn9ce708189264")
    @GET("/games/{id}")
    suspend fun fetchGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY
    ): GameDetailResponse
}