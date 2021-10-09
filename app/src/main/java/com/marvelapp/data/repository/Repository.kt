package com.marvelapp.data.repository

import com.marvelapp.model.CharactersResponse


interface Repository {
    suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse?
    suspend fun getCharacterById(characterId:Int):CharactersResponse?

}