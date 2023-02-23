package com.iothar.android.api.service

import com.iothar.android.api.model.SetsChunk
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface PokemonSetsService {

    // <<-METHOD->>
    @Headers("X-Api-Key: 1b952091-9162-4bba-8f9c-a429ff56f44d")
    @GET("/sets")
    fun listSets(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("orderBy") orderBy: String?
    ): Call<SetsChunk>

}