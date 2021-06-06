package com.burkclik.videogamesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.videogamesapp.databinding.ItemGamesPagerBinding
import com.burkclik.videogamesapp.domain.model.Games

class GamesHomeViewPagerAdapter() :
    ListAdapter<Games, GamesHomeViewPagerAdapter.GameHomePagerViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Games) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHomePagerViewHolder {
        val binding =
            ItemGamesPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameHomePagerViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: GameHomePagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GameHomePagerViewHolder(
        private val binding: ItemGamesPagerBinding,
        private val itemClickListener: (Games) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Games) {
            binding.game = game
            binding.executePendingBindings()

            binding.imageViewGame.setOnClickListener {
                itemClickListener(game)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Games>() {
            override fun areItemsTheSame(oldItem: Games, newItem: Games) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Games, newItem: Games) = oldItem == newItem
        }
    }
}