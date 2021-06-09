package com.burkclik.videogamesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.burkclik.videogamesapp.common.BaseViewModel
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
    BaseViewModel() {
    private val _game: MutableLiveData<GameDetail> = MutableLiveData()
    val game: LiveData<GameDetail> = _game

    private val _favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val favoriteState: LiveData<Boolean> = _favoriteState

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private val gameId: Int = savedStateHandle["gameId"]!!

    fun fetchGame() {
        viewModelScope.launch {
            gameDetailUseCase.fetchDetailGame(gameId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _game.value = resource.data
                        _loadingState.value = false
                    }
                    is Resource.Error -> {
                        _errorLiveData.value = resource.exception?.message ?: ""
                        _loadingState.value = false
                    }
                    is Resource.Loading -> {
                        _loadingState.value = true
                    }
                }
            }
        }
    }

    fun getGameById() {
        viewModelScope.launch {
            gameDetailUseCase.getGameById(gameId).collect {
                _favoriteState.value = it.favorite
            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch {
            gameDetailUseCase.updateFavorite(!_favoriteState.value!!, gameId)
        }
    }
}