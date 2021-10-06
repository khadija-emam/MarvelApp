package com.marvelapp.data.remotedata

import com.marvelapp.model.CharactersResponse


interface RemoteDataSource {
suspend fun getCharaters(): CharactersResponse?
}