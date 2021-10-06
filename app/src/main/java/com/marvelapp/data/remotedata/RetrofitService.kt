package com.marvelapp.data.remotedata


import com.marvelapp.di.API_KEY
import com.marvelapp.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("v1/public/characters?apikey=63b1f94dad044add871d1e319c630265")
   suspend fun getCharacters(@Query("apiKey")apiKey:String= API_KEY):Response<CharactersResponse>

}