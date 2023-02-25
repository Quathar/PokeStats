package com.iothar.android.api.service

import com.iothar.android.api.model.SetsChunk
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonSetsService {

    // <<-CONSTANTS->>
    companion object {
        const val PAGE_SIZE = 5
        const val ORDER_BY = "-releaseDate"
    }

    // <<-METHOD->>
    @GET("sets")
    fun listSets(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = PAGE_SIZE,
        @Query("orderBy") orderBy: String = ORDER_BY
    ): Call<SetsChunk>

}