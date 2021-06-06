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

    private val gameId: Int = savedStateHandle["gameId"]!!

    init {
        fetchGame()
    }

    private fun fetchGame() {
        viewModelScope.launch {
            gameDetailUseCase.fetchDetailGame(gameId).collect { resource ->
                when (resource) {
                    is Resource.Success -> _game.value = resource.data
                    is Resource.Error -> Log.i("Burak", "${resource.exception?.message}")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }
}