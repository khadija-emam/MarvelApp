package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse
import com.marvelapp.model.DetailsData
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

    override suspend fun getImages(characterId: Int, type: String): DetailsData? {
        val result=retrofitService.getImage(characterId,type)
        if (result.isSuccessful){
            return result.body()?.data
        }else{
            throw Exception(result.message())
        }
    }
}