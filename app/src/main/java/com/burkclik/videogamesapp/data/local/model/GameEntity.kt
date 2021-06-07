package com.burkclik.videogamesapp.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val released: String,
    @ColumnInfo(name = "background_image") val backgroundImage: String,
    val rating: Double,
    val favorite: Boolean = false,
)
