package com.marvelapp.data.repository

import com.marvelapp.data.remotedata.RemoteDataSource
import com.marvelapp.model.CharactersResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):
    Repository {
    override suspend fun getCharacters(): CharactersResponse? {
        return remoteDataSource.getCharaters()
    }
}