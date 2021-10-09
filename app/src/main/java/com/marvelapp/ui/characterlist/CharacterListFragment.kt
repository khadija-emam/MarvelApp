package com.marvelapp.ui.characterlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.EndlessRecyclerViewScrollListener
import com.marvelapp.R
import com.marvelapp.databinding.CharacterListFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    lateinit var binding: CharacterListFragmentBinding
    lateinit var adapter: CharactersAdapter

    private  val viewModel: CharacterListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_list_fragment, container, false)
        binding.lifecycleOwner = this

        setUpAdapter()
        observeForMessage()
        observeForCharacters()
        observeForLoading()
        observeNavigation()
        observeForNoCharacters()
        navigateToSearch()
        return binding.root
    }

    private fun navigateToSearch(){
        binding.search.setOnClickListener{
            findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToSearchFragment(
                adapter.currentList.toTypedArray()
            ))
        }
    }
    private fun observeNavigation() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(it.id))
                viewModel.completeNavigation()
            }
        }
    }

    private fun observeForLoading() {
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE

            }
        })
    }
    private fun observeForNoCharacters() {
        viewModel.noCharacter.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.noNews.visibility = View.VISIBLE
            } else {
                binding.noNews.visibility = View.GONE

            }
        })
    }

    private fun observeForCharacters() {
        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun observeForMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpAdapter() {
        adapter = CharactersAdapter(CharacterClickListener { viewModel.onItemClicked(it) })
        binding.characterRv.adapter = adapter
        binding.characterRv.addOnScrollListener(
            object : EndlessRecyclerViewScrollListener(binding.characterRv.layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    viewModel.getCharacters()
                }
            }
        )

    }



}