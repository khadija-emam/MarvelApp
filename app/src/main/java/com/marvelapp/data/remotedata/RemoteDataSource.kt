package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse
import com.marvelapp.model.DetailsData


interface RemoteDataSource {
suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse?
suspend fun getCharacterById(characterId:Int):CharactersResponse?
suspend fun getImages(characterId:Int,type:String):DetailsData?

}