package com.burkclik.videogamesapp.common

import androidx.lifecycle.ViewModel
import com.burkclik.videogamesapp.common.navigation.Navigation

abstract class BaseViewModel : ViewModel() {
    val navigation = Navigation()
}