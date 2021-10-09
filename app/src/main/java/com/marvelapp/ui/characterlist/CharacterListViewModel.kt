package com.marvelapp.ui.characterlist

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
class CharacterListViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>>
        get() = _charactersList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress
    private val _navigate = MutableLiveData<Character?>()
    val navigate: LiveData<Character?>
        get() = _navigate
    private var limit = 20
    private var offset = 1

    init {
        getCharacters()
    }


    fun getCharacters() {
        viewModelScope.launch {
            _progress.value = true
            val result = repository.getCharacters(limit, offset)
            try {
                if (!result?.data?.characters.isNullOrEmpty()) {
                    _progress.value = false
                    val list = mutableListOf<Character>().apply {
                        _charactersList.value?.let { addAll(it) }
                        result?.data?.characters?.let { addAll(it) }
                    }
                    _charactersList.value = list

                    offset += limit

                } else {
                    _progress.value = false
                }
            } catch (e: Exception) {

                _progress.value = false
                _errorMessage.value = e.message
            }
        }
    }

    fun onItemClicked(character: Character) {
        _navigate.value = character

    }


    fun completeNavigation() {
        _navigate.value = null

    }
}