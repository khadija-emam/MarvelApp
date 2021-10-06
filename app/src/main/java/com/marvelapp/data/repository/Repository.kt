package com.marvelapp.data.repository

import com.marvelapp.model.CharactersResponse


interface Repository {
    suspend fun getCharacters(): CharactersResponse?
}