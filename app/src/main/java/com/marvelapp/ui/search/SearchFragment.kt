package com.marvelapp.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marvelapp.R
import com.marvelapp.databinding.CharacterListFragmentBinding
import com.marvelapp.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: SearchFragmentBinding

    private  val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        binding.lifecycleOwner = this
        search()
        navigateBack()

        return binding.root
    }

    private fun navigateBack(){
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun search(){
//        binding.searchEd.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                viewModel.filter(s.toString())
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//        })
    }

}