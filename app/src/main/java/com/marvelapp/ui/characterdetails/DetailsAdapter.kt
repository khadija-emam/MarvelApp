package com.marvelapp.ui.characterdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.CharacterItemBinding
import com.marvelapp.databinding.DetailsItemBinding
import com.marvelapp.model.Character
import com.marvelapp.model.Items
import com.marvelapp.ui.characterlist.CharactersAdapter

class DetailsAdapter  (val onClickListener: DetailsClickListener) : ListAdapter<Items, DetailsAdapter.DetailsViewHolder>(DetailsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { item?.let { onClickListener.onClick(it) } }

    }

    class DetailsViewHolder private constructor(val binding: DetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Items) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): DetailsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DetailsItemBinding.inflate(layoutInflater, parent, false)
                return DetailsViewHolder(binding)
            }
        }
    }
}


class DetailsDiffCallback : DiffUtil.ItemCallback<Items>() {
    override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem.resourceURI == newItem.resourceURI
    }

    override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
        return oldItem == newItem
    }
}

class DetailsClickListener(val onClick: (item:Items) -> Unit)
