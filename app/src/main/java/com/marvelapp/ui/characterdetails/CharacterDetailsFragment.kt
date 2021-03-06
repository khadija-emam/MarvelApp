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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marvelapp.R
import com.marvelapp.bindCharacterImage
import com.marvelapp.databinding.CharacterDetailsFragmentBinding
import com.marvelapp.databinding.SearchFragmentBinding
import com.marvelapp.model.Character
import com.marvelapp.ui.characterlist.CharacterClickListener
import com.marvelapp.ui.characterlist.CharacterListFragmentDirections
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
        binding.viewModel=viewModel
        viewModel.getCharacterData(args.id)

        setUpAdapter()

        observeForCharacter()
        observeForComics()
        observeForEvents()
        observeForSeries()
        observeForStories()
        observeNavigation()
        observeForLoading()
        observeForMessage()

        return binding.root
    }
    private fun setCharacterData(character:Character){
        binding.content.characterName.text=character.name
        if (character.description.isNotEmpty()){
            binding.content.characterDesc.text=character.description

        }else{
            binding.content.desc.visibility=View.GONE
        }
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

    private fun observeNavigation() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToPhotoViewFragment(it))
                viewModel.completeNavigation()
            }
        }
    }

    private fun setUpAdapter() {
        comicsAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.content.comicsRv.adapter = comicsAdapter

       eventsAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.content.eventsRv.adapter = eventsAdapter

        seriesAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.content.seriesRv.adapter = seriesAdapter

        storiesAdapter = DetailsAdapter(DetailsClickListener { viewModel.onItemClicked(it) })
        binding.content.storiesRv.adapter = storiesAdapter
    }
    private fun observeForCharacter() {
        viewModel.charactersList.observe(viewLifecycleOwner, Observer {
            setCharacterData(it[0])

        })
    }
    private fun observeForComics() {
        viewModel.comicsList.observe(viewLifecycleOwner, Observer {
         if (it.isNotEmpty()) {
             comicsAdapter.submitList(it)
         }else{
             binding.content.comics.visibility=View.GONE
             binding.content.comicsRv.visibility=View.GONE
         }
        })
    }
    private fun observeForEvents() {
        viewModel.eventsList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                eventsAdapter.submitList(it)
            }else{
                binding.content.events.visibility=View.GONE
                binding.content.eventsRv.visibility=View.GONE
            }
        })
    }
    private fun observeForSeries() {
        viewModel.seriesList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                seriesAdapter.submitList(it)
            }else{
                binding.content.series.visibility=View.GONE
                binding.content.seriesRv.visibility=View.GONE
            }
        })
    }
    private fun observeForStories() {
        viewModel.storiesList.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                storiesAdapter.submitList(it)
            }else{
                binding.content.stories.visibility=View.GONE
                binding.content.storiesRv.visibility=View.GONE
            }
        })
    }
}