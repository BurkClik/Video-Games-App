package com.burkclik.videogamesapp.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.burkclik.videogamesapp.common.navigation.NavigationObserver

abstract class BaseFragment : Fragment() {
    abstract val viewModel: BaseViewModel

    private val navigationObserver = NavigationObserver()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationObserver.observer(viewModel.navigation, findNavController(), viewLifecycleOwner)
    }
}