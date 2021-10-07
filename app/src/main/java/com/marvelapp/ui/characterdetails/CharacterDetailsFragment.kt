package com.marvelapp.ui.characterdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.marvelapp.R
import com.marvelapp.databinding.CharacterDetailsFragmentBinding
import com.marvelapp.databinding.SearchFragmentBinding
import com.marvelapp.ui.search.SearchViewModel

class CharacterDetailsFragment : Fragment() {

    lateinit var binding: CharacterDetailsFragmentBinding


    private  val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.character_details_fragment, container, false)
        binding.lifecycleOwner = this


        return binding.root
    }



}