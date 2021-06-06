package com.burkclik.videogamesapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.burkclik.videogamesapp.R
import com.burkclik.videogamesapp.common.navigation.NavigationObserver
import com.burkclik.videogamesapp.databinding.FragmentGameHomeBinding
import com.burkclik.videogamesapp.domain.model.Games
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameHomeFragment : Fragment() {
    private var _binding: FragmentGameHomeBinding? = null
    private val binding get() = _binding!!

    private val imagesList: MutableList<Games> = mutableListOf()
    private var gameList: MutableList<Games> = mutableListOf()

    private val viewModel: GameHomeViewModel by viewModels()

    private val gameAdapter = GamesAdapter()
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

        binding.viewPagerGames.apply {
            adapter = pagerAdapter
        }

        pagerAdapter.itemClickListener = viewModel.itemClickListener

        // For döngüsünü viewModel'da yap!!
        viewModel.games.observe(viewLifecycleOwner) {
            gameList = it.toMutableList()
            for (i in 0..2) {
                imagesList.add(it[i])
                gameList.removeFirst()
            }
            pagerAdapter.submitList(imagesList)
            binding.circleIndicatorViewPager.setViewPager(binding.viewPagerGames)
            gameAdapter.submitList(gameList)
        }
    }

    override fun onDestroyView() {
        imagesList.clear()
        super.onDestroyView()
    }

}