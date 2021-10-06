package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val retrofitService: RetrofitService):
    RemoteDataSource {
    override suspend fun getCharaters(): CharactersResponse? {
        val result=retrofitService.getCharacters()
        if (result.isSuccessful){
            return result.body()
        }else{
            throw Exception(result.message())
        }
    }
}