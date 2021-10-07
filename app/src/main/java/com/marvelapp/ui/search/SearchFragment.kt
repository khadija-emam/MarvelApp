package com.marvelapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marvelapp.R
import com.marvelapp.databinding.SearchFragmentBinding
import com.marvelapp.ui.characterlist.CharacterClickListener
import com.marvelapp.ui.characterlist.CharactersAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
     val args by navArgs<SearchFragmentArgs>()
    lateinit var binding: SearchFragmentBinding

    private  val viewModel: SearchViewModel by viewModels()
    lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        binding.lifecycleOwner = this
        setAdapter()
        getCharacterList()
      //  setGravity()
        search()
        navigateBack()
        observeForFiltered()
        observeForNavigation()

        return binding.root
    }

    private fun getCharacterList(){
        Log.i("TAG", "getCharacterList: ${args.characters}")
        viewModel.charactersList=args.characters.toList()
    }

//     private fun setGravity(){
//        val window: Window? = dialog!!.window
//
//        // set "origin" to top left corner, so to speak
//
//        // set "origin" to top left corner, so to speak
//        window?.setGravity(Gravity.TOP)
//
//
//    }
    private fun navigateBack(){
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun setAdapter(){
        adapter = SearchAdapter(CharacterClickListener { viewModel.onItemClicked(it) })
        binding.searchCharacterRv.adapter = adapter
    }

    private fun search(){
        binding.searchEd.doAfterTextChanged {
            viewModel.filter(it.toString())
        }
    }
    private fun observeForFiltered(){
        viewModel.filteredList.observe(viewLifecycleOwner, Observer {

            adapter.submitList(it)
        })
    }
    private fun observeForNavigation(){
        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToCharacterDetailsFragment())
        })
    }

}