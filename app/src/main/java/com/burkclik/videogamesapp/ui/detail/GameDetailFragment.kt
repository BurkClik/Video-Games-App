package com.burkclik.videogamesapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.burkclik.videogamesapp.common.BaseFragment
import com.burkclik.videogamesapp.databinding.FragmentGameDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailFragment : BaseFragment() {

    private lateinit var binding: FragmentGameDetailBinding
    override val viewModel: GameDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setUpViewModel()
        return binding.root
    }

    private fun setUpViewModel() {
        with(viewModel) {
            fetchGame()
            getGameById()

            errorLiveData.observe(viewLifecycleOwner) {
                showSnack(it, requireView())
            }
        }
    }
}