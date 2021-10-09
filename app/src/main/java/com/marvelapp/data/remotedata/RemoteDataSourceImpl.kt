package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val retrofitService: RetrofitService):
    RemoteDataSource {
    override suspend fun getCharacters(limit: Int, offset: Int): CharactersResponse? {
        val result=retrofitService.getCharacters(limit,offset)
        if (result.isSuccessful){
            return result.body()
        }else{
            throw Exception(result.message())
        }
    }

    override suspend fun getCharacterById(characterId: Int): CharactersResponse? {
        val result=retrofitService.getCharacterById(characterId)
        if (result.isSuccessful){
            return result.body()
        }else{
            throw Exception(result.message())
        }    }
}