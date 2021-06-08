package com.burkclik.videogamesapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.burkclik.videogamesapp.R
import com.burkclik.videogamesapp.common.BaseFragment
import com.burkclik.videogamesapp.common.GenericAdapter
import com.burkclik.videogamesapp.databinding.FragmentFavoriteBinding
import com.burkclik.videogamesapp.domain.model.Game
import com.burkclik.videogamesapp.ui.home.GamesAdapterDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment() {
    private lateinit var binding: FragmentFavoriteBinding

    override val viewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter = GenericAdapter<Game>(R.layout.item_game)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpView()
        return binding.root
    }

    private fun setUpView() {
        with(viewModel) {
            getFavorites()

            binding.recyclerViewFavorites.apply {
                adapter = favoriteAdapter
                addItemDecoration(GamesAdapterDecoration())
            }

            favoriteAdapter.itemClickListener = itemClickListener

            favorites.observe(viewLifecycleOwner) {
                favoriteAdapter.submitList(it)
            }
        }
    }
}