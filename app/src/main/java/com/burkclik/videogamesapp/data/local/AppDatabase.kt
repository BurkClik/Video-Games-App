package com.burkclik.videogamesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.burkclik.videogamesapp.data.local.model.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}