package com.marvelapp.data.remotedata


import com.marvelapp.di.API_KEY
import com.marvelapp.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(): Response<CharactersResponse>
}