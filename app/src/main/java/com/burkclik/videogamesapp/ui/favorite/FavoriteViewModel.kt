package com.burkclik.videogamesapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.burkclik.videogamesapp.common.BaseViewModel
import com.burkclik.videogamesapp.domain.FavoriteUseCase
import com.burkclik.videogamesapp.domain.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) :
    BaseViewModel() {
    private val _favorites: MutableLiveData<List<Game?>> = MutableLiveData()
    val favorites: LiveData<List<Game?>> = _favorites

    fun getFavorites() {
        viewModelScope.launch {
            favoriteUseCase.getFavorites().collect {
                _favorites.value = it
            }
        }
    }

    val itemClickListener: (Game) -> Unit = {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToGameDetailFragment(it.id)
        navigation.navigate(action)
    }
}