package com.burkclik.videogamesapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.common.navigation.Navigation
import com.burkclik.videogamesapp.domain.GameUseCase
import com.burkclik.videogamesapp.domain.model.Games
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameHomeViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    private val _games: MutableLiveData<List<Games>> = MutableLiveData()
    val games: LiveData<List<Games>> = _games

    val navigation = Navigation()

    val itemClickListener: (Games) -> Unit = {
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
                    is Resource.Success -> _games.value = resource.data
                    is Resource.Error -> Log.i("Burak", "${resource.exception?.message}")
                    Resource.Loading -> Log.i("Burak", "Loading")
                }
            }
        }
    }
}