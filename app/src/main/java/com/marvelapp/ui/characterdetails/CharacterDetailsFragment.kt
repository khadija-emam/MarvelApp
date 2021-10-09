package com.marvelapp.ui.characterdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.marvelapp.R
import com.marvelapp.bindCharacterImage
import com.marvelapp.databinding.CharacterDetailsFragmentBinding
import com.marvelapp.databinding.SearchFragmentBinding
import com.marvelapp.model.Character
import com.marvelapp.ui.characterlist.CharacterClickListener
import com.marvelapp.ui.characterlist.CharactersAdapter
import com.marvelapp.ui.search.SearchFragmentArgs
import com.marvelapp.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    lateinit var binding: CharacterDetailsFragmentBinding
    val args by navArgs<CharacterDetailsFragmentArgs>()
    lateinit var comicsAdapter: DetailsAdapter
    lateinit var eventsAdapter: DetailsAdapter
    lateinit var seriesAdapter: DetailsAdapter
    lateinit var storiesAdapter: DetailsAdapter


    private  val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_details_fragment, container, false)
        binding.lifecycleOwner = this
        viewModel.getCharacterData(args.id)

        setUpAdapter()

        observeForCharacter()
        observeForLoading()
        observeForMessage()

        return binding.root
    }
    private fun setCharacterData(character:Character){
        binding.characterName.text=character.name
        binding.characterDesc.text=character.description
        bindCharacterImage(binding.characterImg,character.thumbnail)
    }
    private fun observeForMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
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

    private fun setUpAdapter() {
        comicsAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.comicsRv.adapter = comicsAdapter

       eventsAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.eventsRv.adapter = eventsAdapter

        seriesAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.seriesRv.adapter = seriesAdapter

        storiesAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.storiesRv.adapter = storiesAdapter
    }
    private fun observeForCharacter() {
        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            setCharacterData(it[0])
           comicsAdapter.submitList(it[0].comics.items)
            eventsAdapter.submitList(it[0].events.items)
            seriesAdapter.submitList(it[0].series.items)
            storiesAdapter.submitList(it[0].stories.items)

        })
    }

}