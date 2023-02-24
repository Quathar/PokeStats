package com.iothar.android.api.service

import com.iothar.android.api.model.SetsChunk
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PokemonSetsService {

    // <<-METHOD->>
    @GET("sets")
    fun listSets(
        @Header("X-Api-Key") auth: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("orderBy") orderBy: String?
    ): Call<SetsChunk>

}