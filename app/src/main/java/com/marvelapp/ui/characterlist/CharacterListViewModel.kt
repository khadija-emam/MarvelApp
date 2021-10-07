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
class CharacterListViewModel @Inject constructor(val repository: Repository)  : ViewModel() {
    private var _response = listOf<Character>()

    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>>
        get() = _charactersList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _noCharacter = MutableLiveData<Boolean>()
    val noCharacter: LiveData<Boolean>
        get() = _noCharacter
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress
    private val _navigate = MutableLiveData<Character?>()
    val navigate: LiveData<Character?>
        get() = _navigate
    init {
        getCharacters()
    }


    private fun getCharacters() {
        viewModelScope.launch {
            _progress.value=true
            val result=repository.getCharacters()
            try {
                if (!result?.data?.characters.isNullOrEmpty()){
                    _progress.value=false
                    _noCharacter.value=false
                    if (result != null) {
                        _response=result.data.characters
                    }
                    _charactersList.value=result?.data?.characters
                }else{
                    _noCharacter.value=true
                    _progress.value=false
                }
            }catch (e: Exception){
                _noCharacter.value=false

                _progress.value=false
                _errorMessage.value=e.message
            }
        }
    }
    fun onItemClicked(character: Character) {
        _navigate.value = character

    }


    fun completeNavigation(){
        _navigate.value=null

    }
}