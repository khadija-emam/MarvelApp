package com.marvelapp.di

import com.marvelapp.HashGenerate
import com.marvelapp.data.remotedata.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Movie API communication setup via Retrofit.
 */
 const val API_KEY = "63b1f94dad044add871d1e319c630265"
private const val NEWS_BASE_URL = "https://gateway.marvel.com"
private const val API_PUBLIC="4dca613e1fb17f606801b604fe4c833b"
private const val API_PRIVATE="93b89f393de8af4f9ab7f8f34988500153cf7944"
        // Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    // Makes Hilt provide Retrofit instance when a Retrofit type is requested
    @Provides
    @Singleton
    fun providesRetrofit(): RetrofitService {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder().client(OkHttpClient())
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        return retrofit.create(RetrofitService::class.java)
    }
    fun createOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor { chain -> createParametersDefault(chain) }
            .addInterceptor(httpLoggingInterceptor).build()

    }
    fun createParametersDefault(chain: Interceptor.Chain): Response {
        val timeStamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
        var request = chain.request()
        val builder = request.url().newBuilder()

        builder.addQueryParameter("apikey", API_PUBLIC)
            .addQueryParameter("hash", HashGenerate.generate(timeStamp, API_PRIVATE, API_PUBLIC))
            .addQueryParameter("ts", timeStamp.toString())

        request = request.newBuilder().url(builder.build()).build()
        return chain.proceed(request)
    }

}