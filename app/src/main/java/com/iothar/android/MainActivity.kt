package com.iothar.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.iothar.android.api.model.Sets
import com.iothar.android.api.model.SetsChunk
import com.iothar.android.api.service.PokemonSetsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = MainActivity::class.java.name
        val PAGE_SIZE = 20
        val ORDER_BY = "-releaseDate"
    }



    // <<-FIELDS->>
    private var _page = 1

    private lateinit var _service: PokemonSetsService

    // <<-METHODS->>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _service = retrofit.create(PokemonSetsService::class.java)

        loadSetChunk(_page,PAGE_SIZE, ORDER_BY)
    }

    private fun loadSetChunk(page: Int, pageSize: Int, orderBy: String) {
        val call: Call<SetsChunk> = _service.listSets(page, pageSize, orderBy)

        call.enqueue(object : Callback<SetsChunk> {
            override fun onResponse(call: Call<SetsChunk>, response: Response<SetsChunk>) {
                if (response.isSuccessful) {
                    val sets: List<Sets> = response.body()!!.data
//                    _species.addAll(species)
                    for (set in sets) {
//                        Log.i(MainActivity.TAG, set.toString())
                        println(set.toString())
                    }
                }
            }

            override fun onFailure(call: Call<SetsChunk>, t: Throwable) {
                println("Something went wrong")
            }
        })
    }

}