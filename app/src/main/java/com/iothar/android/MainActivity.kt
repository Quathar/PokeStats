package com.iothar.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iothar.android.api.helpers.AuthInterceptor
import com.iothar.android.api.model.Sets
import com.iothar.android.api.model.SetsChunk
import com.iothar.android.api.service.PokemonSetsService
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        const val API_BASE_URL = "https://api.pokemontcg.io/v2/"
        val TAG: String = MainActivity::class.java.name
    }

    // <<-FIELDS->>
    private var _page = 1

    private lateinit var _service: PokemonSetsService

    // <<-METHODS->>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _service = retrofit.create(PokemonSetsService::class.java)

        loadSetChunk(_page)
    }

    private fun loadSetChunk(page: Int) {
        _service.listSets(page)
            .enqueue(object : Callback<SetsChunk> {
                override fun onResponse(call: Call<SetsChunk>, response: Response<SetsChunk>) {
                    if (response.isSuccessful) {
                        val sets: List<Sets> = response.body()!!.data
//                        _species.addAll(species)
                        for (set in sets)
                            Log.i(TAG, set.toString())
                        _page++
                    } else Log.e(TAG, response.errorBody().toString())
                }

                override fun onFailure(call: Call<SetsChunk>, t: Throwable) {
                    println("Something went wrong")
                }
            })
    }
}