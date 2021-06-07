package com.burkclik.videogamesapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.burkclik.videogamesapp.data.local.model.GameEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM games")
    suspend fun getAllGames(): List<GameEntity>

    @Query("SELECT * FROM games WHERE name LIKE '%' || :search || '%'")
    suspend fun searchGame(search: String?): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(games: List<GameEntity>)

    @Query("SELECT * FROM games WHERE favorite = 1")
    suspend fun getAllFavorites(): List<GameEntity>

    @Query("UPDATE games SET favorite = :state WHERE id = :id")
    suspend fun updateFavorite(state: Boolean, id: Int)

    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun getGameById(id: Int): GameEntity
}