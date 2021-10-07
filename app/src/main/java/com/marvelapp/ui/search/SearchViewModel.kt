package com.marvelapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelapp.data.repository.Repository
import com.marvelapp.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(val repository: Repository) : ViewModel() {
     var charactersList = listOf<Character>()

    private val _filteredList = MutableLiveData<List<Character>>()
    val filteredList: LiveData<List<Character>>
        get() = _filteredList

    private val _navigate = MutableLiveData<Character?>()
    val navigate: LiveData<Character?>
        get() = _navigate

    fun filter(searchWord: String) {
        if (searchWord.isNotEmpty()) {
            val filteredItem = charactersList.filter { product ->
                product.name.contains(searchWord)
            }
            _filteredList.value= filteredItem
        } else {
            _filteredList.value = emptyList()
        }
    }



    fun onItemClicked(character: Character) {
        _navigate.value = character

    }

}