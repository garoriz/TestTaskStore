package com.garif.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://run.mocky.io/v3/"

@Module
class NetworkModule {
    @Provides
    fun getOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideGsonConverter(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideApi(
        provideOkHttpClient: OkHttpClient,
        provideGsonConverterFactory: GsonConverterFactory,
    ): Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient)
        .addConverterFactory(provideGsonConverterFactory)
        .build()
        .create(Api::class.java)
}