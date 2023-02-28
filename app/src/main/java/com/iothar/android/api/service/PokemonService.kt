package com.iothar.android.api.service

import com.iothar.android.api.model.CardsChunk
import com.iothar.android.api.model.CardsDetails
import com.iothar.android.api.model.SetsChunk
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    // <<-CONSTANTS->>
    companion object {
        private const val SETS_SIZE = 5
        private const val CARDS_SIZE = 9
        private const val ORDER_BY = "-releaseDate"
        private const val SET_ID = "set.id:%s"
    }

    // <<-METHOD->>
    @GET("sets")
    fun listSets(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = SETS_SIZE,
        @Query("orderBy") orderBy: String = ORDER_BY
    ): Call<SetsChunk>

    @GET("cards")
    fun listCards(
        @Query("page") page: Int,
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int = CARDS_SIZE
    ): Call<CardsChunk>

    @GET("cards/{id}")
    fun cardDetails(
        @Path("id") id: String
    ): Call<CardsDetails>

}