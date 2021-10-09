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
import com.marvelapp.model.Detail
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

    private val _comicsList = MutableLiveData<List<Detail>>()
    val comicsList: LiveData<List<Detail>>
        get() = _comicsList

    private val _eventsList = MutableLiveData<List<Detail>>()
    val eventsList: LiveData<List<Detail>>
        get() = _eventsList

    private val _seriesList = MutableLiveData<List<Detail>>()
    val seriesList: LiveData<List<Detail>>
        get() = _seriesList

    private val _storiesList=MutableLiveData<List<Detail>>()
    val storiesList: LiveData<List<Detail>>
        get() = _storiesList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _navigate = MutableLiveData<Thumbnail?>()
    val navigate: LiveData<Thumbnail?>
        get() = _navigate

    fun getCharacterData(id:Int) {
        viewModelScope.launch {
            _progress.value = true
            val result = repository.getCharacterById(id)
            try {
                if (result != null) {
                    if (!result.data.characters.isNullOrEmpty()) {
                        _progress.value = false
                        _charactersList.value = result.data.characters
                        getComics(id)
                        getEvents(id)
                        getSeries(id)
                        getStories(id)

                    }
                }

            } catch (e: Exception) {
                _errorMessage.value = e.message
                _progress.value = false
            }
        }
    }

   private fun getComics(characterId:Int){
        viewModelScope.launch {
            val res=repository.getImages(characterId,"comics")

                if (res != null) {
                 _comicsList.value=res.detail
                    Log.i("TAG", "getComics: ${res.detail[0].name}")
                }

        }
    }
   private fun getEvents(characterId:Int){
        viewModelScope.launch {
            val res=repository.getImages(characterId,"events")
    
            if (res != null) {
                _eventsList.value=res.detail
            }

        }
    }

  private  fun getStories(characterId:Int){
        viewModelScope.launch {
            val res=repository.getImages(characterId,"stories")

            if (res != null) {
                _storiesList.value=res.detail
            }

        }
    }
    private fun getSeries(characterId:Int){
        viewModelScope.launch {
            val res=repository.getImages(characterId,"series")

            if (res != null) {
                _seriesList.value=res.detail
            }

        }
    }

    fun onItemClicked(detail: Detail) {
     _navigate.value=detail.thumbnail

    }
    fun completeNavigation() {
        _navigate.value = null

    }
}