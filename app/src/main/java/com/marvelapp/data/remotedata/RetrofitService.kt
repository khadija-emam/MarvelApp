package com.marvelapp.data.remotedata


import com.marvelapp.model.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface RetrofitService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("limit") limit: Int, @Query("offset") offset: Int): Response<CharactersResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int

    ): Response<CharactersResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getImage(
        @Path("characterId") characterId: Int,
        @QueryName name:String
    )
}