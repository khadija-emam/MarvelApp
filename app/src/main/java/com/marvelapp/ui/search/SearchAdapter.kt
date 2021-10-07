package com.marvelapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.CharacterItemBinding
import com.marvelapp.databinding.SearchItemBinding
import com.marvelapp.model.Character
import com.marvelapp.ui.characterlist.CharacterClickListener
import com.marvelapp.ui.characterlist.CharacterDiffCallback
import com.marvelapp.ui.characterlist.CharactersAdapter

class SearchAdapter (val onClickListener: CharacterClickListener) : ListAdapter<Character, SearchAdapter.SearchViewHolder>(
    CharacterDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
        holder.itemView.setOnClickListener { character?.let { onClickListener.onClick(it) } }


    }

    class SearchViewHolder private constructor(val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SearchViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SearchItemBinding.inflate(layoutInflater, parent, false)
                return SearchViewHolder(binding)
            }
        }
    }
 }



