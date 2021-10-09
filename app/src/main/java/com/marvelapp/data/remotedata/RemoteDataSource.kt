package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse


interface RemoteDataSource {
suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse?
suspend fun getCharacterById(characterId:Int):CharactersResponse?

}