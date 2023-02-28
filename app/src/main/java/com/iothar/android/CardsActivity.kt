package com.iothar.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iothar.android.api.helper.PokemonAPI
import com.iothar.android.api.model.Cards
import com.iothar.android.api.model.CardsChunk
import com.iothar.android.recycler.adapter.CardsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardsActivity : AppCompatActivity() {

    // <<-CONSTANTS->>
    companion object {
        private val TAG: String = CardsActivity::class.java.name
        const val ID_KEY = "ID"

        private const val SET_ID = "set.id:%s"
    }

    // <<-FIELDS->>
    private lateinit var _cardsAdapter: CardsAdapter
    private lateinit var _recyclerCards: RecyclerView
    private lateinit var _setId: String
    private val _service = PokemonAPI.pokemonService
    private var _cards = ArrayList<Cards>()
    private var _page = 1

    // <<-METHODS->>
    override fun onCreate(savedInstanceState: Bundle?) {
        // Si pones esto no va
        // persistentState: PersistableBundle?
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        _setId = intent.getStringExtra(ID_KEY).toString()

        initRecyclerCards()
        loadCardsChunk()
    }

    private fun initRecyclerCards() {
        _recyclerCards = findViewById(R.id.recycler_cards)
        _recyclerCards.layoutManager = GridLayoutManager(this@CardsActivity, 3)
        _cardsAdapter = CardsAdapter(_cards, object : CardsAdapter.OnCardsClickListener {
            override fun onCardsClick(cards: Cards) {
                startActivity(
                    Intent(this@CardsActivity, CardsDetailsActivity::class.java)
                        .apply { putExtra(CardsDetailsActivity.ID_KEY, cards.id) }
                )
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
        _service.listCards(_page, String.format(SET_ID, _setId))
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