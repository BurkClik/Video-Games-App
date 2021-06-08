package com.burkclik.videogamesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.burkclik.videogamesapp.R
import com.burkclik.videogamesapp.common.BaseFragment
import com.burkclik.videogamesapp.common.GenericAdapter
import com.burkclik.videogamesapp.databinding.FragmentGameHomeBinding
import com.burkclik.videogamesapp.domain.model.Games
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameHomeFragment : BaseFragment() {
    private var _binding: FragmentGameHomeBinding? = null
    private val binding get() = _binding!!

    override val viewModel: GameHomeViewModel by viewModels()

    private val gameAdapter = GenericAdapter<Games>(R.layout.item_game)
    private val pagerAdapter = GamesHomeViewPagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recyclerViewGames.adapter = gameAdapter
            recyclerViewGames.addItemDecoration(GamesAdapterDecoration())
            viewPagerGames.adapter = pagerAdapter
        }

        gameAdapter.itemClickListener = viewModel.itemClickListener
        pagerAdapter.itemClickListener = viewModel.itemClickListener


        with(viewModel) {
            games.observe(viewLifecycleOwner) {
                when {
                    it.size > 3 -> {
                        pagerAdapter.submitList(it.subList(0, 3))
                        gameAdapter.submitList(it.subList(3, it.size))
                    }
                    viewModel.searchText.value!!.length >= 3 -> {
                        gameAdapter.submitList(it)
                    }
                }


                with(binding) {
                    circleIndicatorViewPager.setViewPager(viewPagerGames)
                }
            }

            searchText.observe(viewLifecycleOwner) {
                searchGame(it)
            }
        }
    }
}