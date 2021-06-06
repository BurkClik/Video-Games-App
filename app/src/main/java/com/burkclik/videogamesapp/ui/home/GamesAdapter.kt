package com.burkclik.videogamesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.videogamesapp.databinding.ItemGameBinding
import com.burkclik.videogamesapp.domain.model.Games

class GamesAdapter : ListAdapter<Games, GamesAdapter.GamesViewHolder>(DIFF_CALLBACK) {

    var itemClickListener: (Games) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamesViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class GamesViewHolder(
        private val binding: ItemGameBinding,
        private val itemClickListener: (Games) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(games: Games) {
            binding.game = games
            binding.executePendingBindings()

            binding.itemGame.setOnClickListener {
                itemClickListener(games)
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