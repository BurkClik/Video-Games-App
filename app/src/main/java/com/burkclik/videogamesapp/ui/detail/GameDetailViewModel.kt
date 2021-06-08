package com.burkclik.videogamesapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.domain.GameDetailUseCase
import com.burkclik.videogamesapp.domain.model.GameDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val gameDetailUseCase: GameDetailUseCase,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel() {
    private val _game: MutableLiveData<GameDetail> = MutableLiveData()
    val game: LiveData<GameDetail> = _game

    private val favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    fun getFavoriteState(): LiveData<Boolean> = favoriteState

    private val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    fun getLoadingState(): LiveData<Boolean> = loadingState

    private val gameId: Int = savedStateHandle["gameId"]!!

    fun fetchGame() {
        viewModelScope.launch {
            gameDetailUseCase.fetchDetailGame(gameId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _game.value = resource.data
                        loadingState.value = false
                    }
                    is Resource.Error -> Log.i("Burak", "${resource.exception?.message}")
                    is Resource.Loading -> {
                        loadingState.value = true
                    }
                }
            }
        }
    }

    fun getGameById() {
        viewModelScope.launch {
            gameDetailUseCase.getGameById(gameId).collect {
                favoriteState.value = it.favorite
            }
        }
    }

    fun showInfo() {
        viewModelScope.launch {
            try {
                gameDetailUseCase.updateFavorite(!favoriteState.value!!, gameId)
            } catch (exception: Exception) {
                Log.i("Burak", "${exception.message}")
            }
        }
    }
}