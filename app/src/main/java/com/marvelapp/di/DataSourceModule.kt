package com.marvelapp.di


import com.marvelapp.data.remotedata.RemoteDataSource
import com.marvelapp.data.remotedata.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModuleBind {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
