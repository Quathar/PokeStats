package com.iothar.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.api.helper.PokemonAPI
import com.iothar.android.api.model.Cards
import com.iothar.android.api.model.CardsChunk
import com.iothar.android.api.model.Sets
import com.iothar.android.recycler.adapter.CardsAdapter
import com.iothar.android.recycler.adapter.SetsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SetActivity : AppCompatActivity() {

    // <<-CONSTANTS->>
    companion object {
        private val TAG: String = MainActivity::class.java.name
        const val ID_KEY = "ID"
    }

    // <<-FIELDS->>
    private lateinit var _cardsAdapter: CardsAdapter
    private lateinit var _recyclerCards: RecyclerView
    private val _service = PokemonAPI.pokemonService
    private var _cards = ArrayList<Cards>()
    private var _page = 1

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_set)

        initRecyclerCards()
        loadCardsChunk()
    }

    private fun initRecyclerCards() {
        _recyclerCards = findViewById(R.id.recycler_sets)
        _recyclerCards.layoutManager = LinearLayoutManager(this)
        _cardsAdapter = CardsAdapter(_cards, object : CardsAdapter.OnCardsClickListener {
            override fun onCardsClick(cards: Cards) {
                val intent = Intent()
                intent.setClass(this@SetActivity, CardActivity::class.java)
//                intent.putExtra(SetActivity.ID_KEY, sets.id)
                startActivity(intent)
            }
        })
        _recyclerCards.adapter = _cardsAdapter

        _recyclerCards.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1))
                    loadCardsChunk()
            }
        })
    }

    private fun loadCardsChunk() {
        _service.listCards(_page)
            .enqueue(object : Callback<CardsChunk> {
                override fun onResponse(call: Call<CardsChunk>, response: Response<CardsChunk>) {
                    if (response.isSuccessful) {
                        val cards = response.body()!!.data
                        if (cards.isNotEmpty()) {
                            _cards.addAll(cards)
                            _cardsAdapter.notifyItemInserted(_page)
                            _page++
                        } else _recyclerCards.clearOnScrollListeners()
                    }
                }

                override fun onFailure(call: Call<CardsChunk>, t: Throwable) {
                    Log.e(TAG, "Network Exception")
                }
            })
    }

}