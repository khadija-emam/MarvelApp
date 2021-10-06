package com.marvelapp.ui.characterlist

import androidx.lifecycle.ViewModel
import com.marvelapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(val repository: Repository)  : ViewModel() {
    // TODO: Implement the ViewModel
}