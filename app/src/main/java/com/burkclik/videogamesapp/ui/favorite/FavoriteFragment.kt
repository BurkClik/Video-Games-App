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
import com.burkclik.videogamesapp.domain.model.Games
import com.burkclik.videogamesapp.ui.home.GamesAdapterDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override val viewModel: FavoriteViewModel by viewModels()

    private val favoriteAdapter = GenericAdapter<Games>(R.layout.item_game)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewFavorites.adapter = favoriteAdapter
        binding.recyclerViewFavorites.addItemDecoration(GamesAdapterDecoration())

        favoriteAdapter.itemClickListener = viewModel.itemClickListener

        viewModel.favorites.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }
    }
}