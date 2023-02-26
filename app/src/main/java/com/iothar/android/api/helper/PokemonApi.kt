package com.iothar.android.api.helper

import com.iothar.android.BuildConfig
import com.iothar.android.api.service.PokemonSetsService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonApi {

    private const val BASE_URL = "https://api.pokemontcg.io/v2/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val setsService: PokemonSetsService by lazy {
        retrofit.create(PokemonSetsService::class.java)
    }
}