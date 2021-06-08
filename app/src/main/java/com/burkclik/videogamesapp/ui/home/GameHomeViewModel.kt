package com.burkclik.videogamesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.burkclik.videogamesapp.common.BaseViewModel
import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.domain.GameUseCase
import com.burkclik.videogamesapp.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameHomeViewModel @Inject constructor(
    private val gameUseCase: GameUseCase,
) : BaseViewModel() {
    private val _games: MutableLiveData<List<Game>> = MutableLiveData()
    val games: LiveData<List<Game>> = _games

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _noResultText: MutableLiveData<Boolean> = MutableLiveData()
    val noResultText: LiveData<Boolean> = _noResultText

    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String> = _errorLiveData

    private var permList: List<Game> = listOf()

    val searchText: MutableLiveData<String?> = MutableLiveData("")

    val itemClickListener: (Game) -> Unit = {
        val action = GameHomeFragmentDirections.actionGameHomeFragmentToGameDetailFragment(it.id)
        navigation.navigate(action)
    }

    init {
        fetchGames()
    }

    private fun fetchGames() {
        viewModelScope.launch {
            gameUseCase.fetchGames().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _games.value = resource.data
                        permList = resource.data
                        _loadingState.value = false
                    }
                    is Resource.Error -> {
                        _errorLiveData.value = resource.exception?.message ?: ""
                        _loadingState.value = false
                    }
                    is Resource.Loading -> {
                        _loadingState.value = true
                        _noResultText.value = false
                    }
                }
            }
        }
    }

    fun searchGame(search: String?) {
        viewModelScope.launch {
            gameUseCase.searchGames(search).collect {
                if (searchText.value!!.length >= 3) {
                    _games.value = it
                    _noResultText.value = it.isEmpty()
                } else if (_games.value?.size != 20) {
                    _games.value = permList
                }
            }
        }
    }
}