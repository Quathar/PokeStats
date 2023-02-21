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

    // <<-FIELDS->>
    private lateinit var _service: PokemonSetsService

    // <<-METHODS->>
    // <<-OVERRIDE->>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.pokemontcg.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _service = retrofit.create(PokemonSetsService::class.java)

        var call = _service.listSets(1, 5, "-releaseDate")

        call!!.enqueue(object : Callback<SetsChunk> {
            override fun onResponse(call: Call<SetsChunk>, response: Response<SetsChunk>) {
                if (response.isSuccessful()) {
                    val body = response.body()?.data
                    println(body);

            }

            fun onFailure(call: Call<SetsChunk>, t: Throwable) {
                println("failed")
            }
        })



//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
    }

}