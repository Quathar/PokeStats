package com.iothar.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var _service: PokemonSetsService
    private var _page = 1
    private lateinit var _sets: List<Sets>
    private lateinit var _adapter: SetsAdapter

    // <<-METHODS->>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // API Calls Configuration
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(BuildConfig.API_KEY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        _service = retrofit.create(PokemonSetsService::class.java)

        // Graphics
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        _adapter = SetsAdapter(_sets, object : SetsAdapter.OnSetsClickListener {
            override fun onSetsClick(sets: Sets) {
                val intent = Intent()
                intent.setClass(this@MainActivity, SetsDetailsActivity::class.java)
                intent.putExtra(SetsDetailsActivity.NAME_KEY, sets.name)
                startActivity(intent)
            }
        })
        recyclerView.adapter = _adapter

        val scrollView = findViewById<ScrollView>(R.id.scrollview)
//        scrollView.setOnScrollChangeListener {
//            fun onScrollChanged() {
//                loadSetChunk(_page)
//            }
//        }

        loadSetChunk(_page)
    }

    private fun loadSetChunk(page: Int) {
        _service.listSets(page)
            .enqueue(object : Callback<SetsChunk> {
                override fun onResponse(call: Call<SetsChunk>, response: Response<SetsChunk>) {
                    if (response.isSuccessful) {
                        val sets: List<Sets> = response.body()!!.data
                        _sets = _sets + sets
                        for (set in sets)
                            Log.i(TAG, set.toString())
                        _adapter.notifyItemChanged(_page)
                        _page++
                    } else Log.e(TAG, response.errorBody().toString())
                }

                override fun onFailure(call: Call<SetsChunk>, t: Throwable) {
                    println("Something went wrong")
                }
            })
    }
}
