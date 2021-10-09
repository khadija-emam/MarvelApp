package com.marvelapp.ui.characterdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marvelapp.HashGenerate
import com.marvelapp.data.repository.Repository
import com.marvelapp.di.API_KEY
import com.marvelapp.di.API_PRIVATE
import com.marvelapp.di.API_PUBLIC
import com.marvelapp.model.Character
import com.marvelapp.model.Items
import com.marvelapp.model.Thumbnail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(val repository: Repository) : ViewModel() {



    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>>
        get() = _charactersList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getCharacterData(id:Int) {
        viewModelScope.launch {
            _progress.value = true
            val result = repository.getCharacterById(id)
            try {
                if (result != null) {
                    if (!result.data.characters.isNullOrEmpty()) {
                        _progress.value = false
                        _charactersList.value = result.data.characters
                        Log.i("TAG", "getCharacterData: ${result.data.characters[0].description} ")
                        Log.i("TAG", "getCharacterData:" +
                                " ${result.data.characters[0].comics.items[0].resourceURI}?apikey=$API_PUBLIC" +
                                "&hash=${HashGenerate.generate(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                                    , API_PRIVATE, API_PUBLIC)}"+"&ts=${TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())}")

                    }
                }

            } catch (e: Exception) {
                _errorMessage.value = e.message
                _progress.value = false
            }
        }
    }


    fun onItemClicked(items: Items) {


    }
}