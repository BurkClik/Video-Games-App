package com.burkclik.videogamesapp.common

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T : BindableItem>(@LayoutRes val layoutId: Int) :
    ListAdapter<T, GenericAdapter.GenericViewHolder<T>>(GenericCallback<T>()) {

    var itemClickListener: (T) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return GenericViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = currentList.size


    class GenericViewHolder<T : BindableItem>(
        private val binding: ViewDataBinding,
        private val itemClickListener: (T) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.baseModel, item)
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                itemClickListener(item)
            }
        }
    }

    class GenericCallback<T : BindableItem> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T) =
            oldItem.toString() == newItem.toString()

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
    }
}