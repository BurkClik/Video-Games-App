package com.burkclik.videogamesapp.data.local

import com.burkclik.videogamesapp.data.local.model.GameEntity
import javax.inject.Inject

class GameLocalDataSource @Inject constructor(private val gameDao: GameDao) {
    suspend fun insertGames(games: List<GameEntity>) = gameDao.insertAll(games)
    suspend fun searchGame(search: String?) = gameDao.searchGame(search)
    suspend fun addFavorite(state: Boolean, id: Int) = gameDao.updateFavorite(state, id)
    suspend fun getFavorites() = gameDao.getAllFavorites()
    suspend fun getGameById(gameId: Int) = gameDao.getGameById(gameId)
}