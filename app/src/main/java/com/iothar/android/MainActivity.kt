package com.iothar.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.api.helper.PokemonAPI
import com.iothar.android.api.model.Sets
import com.iothar.android.api.model.SetsChunk
import com.iothar.android.recycler.adapter.SetsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // <<-CONSTANTS->>
    companion object {
        private val TAG: String = MainActivity::class.java.name
    }

    // <<-FIELDS->>
    private lateinit var _setsAdapter: SetsAdapter
    private lateinit var _recyclerSets: RecyclerView
    private val _service = PokemonAPI.pokemonService
    private var _sets = ArrayList<Sets>()
    private var _page = 1

    // <<-METHODS->>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerSets()
        loadSetsChunk()
    }

    private fun initRecyclerSets() {
        _recyclerSets = findViewById(R.id.recycler_sets)
        _recyclerSets.layoutManager = LinearLayoutManager(this)
        _setsAdapter = SetsAdapter(_sets, object : SetsAdapter.OnSetClickListener {
            override fun onSetsClick(sets: Sets) {
                val intent = Intent()
                intent.setClass(this@MainActivity, SetActivity::class.java)
//                intent.putExtra(SetActivity.ID_KEY, sets.id)
                startActivity(intent)
            }
        })
        _recyclerSets.adapter = _setsAdapter

        _recyclerSets.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1))
                    loadSetsChunk()
            }
        })
    }

    private fun loadSetsChunk() {
        _service.listSets(_page)
            .enqueue(object : Callback<SetsChunk> {
                override fun onResponse(call: Call<SetsChunk>, response: Response<SetsChunk>) {
                    if (response.isSuccessful) {
                        val sets = response.body()!!.data
                        if (sets.isNotEmpty()) {
                            _sets.addAll(sets)
                            _setsAdapter.notifyItemInserted(_page)
                            _page++
                        } else _recyclerSets.clearOnScrollListeners()
                    }
                }

                override fun onFailure(call: Call<SetsChunk>, t: Throwable) {
                    Log.e(TAG, "Network Exception")
                }
            })
    }
}