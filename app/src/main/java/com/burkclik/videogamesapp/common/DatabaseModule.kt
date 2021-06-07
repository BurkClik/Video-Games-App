package com.burkclik.videogamesapp.common

import android.content.Context
import androidx.room.Room
import com.burkclik.videogamesapp.data.local.AppDatabase
import com.burkclik.videogamesapp.data.local.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DatabaseModule {

    @ActivityRetainedScoped
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "app-db")
            .build()

    @ActivityRetainedScoped
    @Provides
    fun provideDao(appDatabase: AppDatabase): GameDao {
        return appDatabase.gameDao()
    }
}