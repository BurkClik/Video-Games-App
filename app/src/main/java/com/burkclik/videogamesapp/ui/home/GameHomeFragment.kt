package com.burkclik.videogamesapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.burkclik.videogamesapp.R
import com.burkclik.videogamesapp.common.BaseFragment
import com.burkclik.videogamesapp.common.GenericAdapter
import com.burkclik.videogamesapp.common.navigation.NavigationObserver
import com.burkclik.videogamesapp.databinding.FragmentGameHomeBinding
import com.burkclik.videogamesapp.domain.model.Games
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameHomeFragment : BaseFragment() {
    private var _binding: FragmentGameHomeBinding? = null
    private val binding get() = _binding!!

    private val imagesList: MutableList<Games> = mutableListOf()
    private var gameList: MutableList<Games> = mutableListOf()

    override val viewModel: GameHomeViewModel by viewModels()

    private val gameAdapter = GenericAdapter<Games>(R.layout.item_game)
    private val pagerAdapter = GamesHomeViewPagerAdapter()

    private val navigationObserver = NavigationObserver()


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
        navigationObserver.observer(viewModel.navigation, findNavController(), viewLifecycleOwner)

        binding.recyclerViewGames.apply {
            adapter = gameAdapter
            addItemDecoration(GamesAdapterDecoration())
        }

        gameAdapter.itemClickListener = viewModel.itemClickListener

        pagerAdapter.itemClickListener = viewModel.itemClickListener

        binding.viewPagerGames.adapter = pagerAdapter

        viewModel.searchText.observe(viewLifecycleOwner) {
            viewModel.searchGame(it)
        }

        // For döngüsünü viewModel'da yap!!
        viewModel.games.observe(viewLifecycleOwner) {
            viewModel._setVisibility.value = gameList.size == 0
            gameList = it.toMutableList()
            if (gameList.size >= 3) {
                for (i in 0..2) {
                    if (imagesList.size < 3) {
                        imagesList.add(it[i])
                    }
                    gameList.removeFirst()
                }
                if (pagerAdapter.itemCount == 0) {
                    pagerAdapter.submitList(imagesList)
                }
                binding.circleIndicatorViewPager.setViewPager(binding.viewPagerGames)
                gameAdapter.submitList(gameList)
            } else {
                imagesList.clear()
                gameAdapter.submitList(gameList)
            }
        }
    }

    // change with onPause
    override fun onDestroyView() {
        imagesList.clear()
        super.onDestroyView()
    }

}