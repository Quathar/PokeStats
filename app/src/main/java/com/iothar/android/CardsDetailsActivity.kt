package com.iothar.android

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iothar.android.api.helper.PokemonAPI
import com.iothar.android.api.model.Cards
import com.iothar.android.api.model.CardsDetails
import com.iothar.android.recycler.adapter.CardsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardsDetailsActivity : AppCompatActivity() {

    // <<-CONSTANTS->>
    companion object {
        private val TAG: String = CardsDetailsActivity::class.java.name
        const val ID_KEY = "ID"
    }

    // <<-FIELDS->>
    private lateinit var _cardId: String
    private val _service = PokemonAPI.pokemonService

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_cards_details)

        _cardId = intent.getStringExtra(ID_KEY).toString()
        loadCard()
    }

    private fun loadCard() {
        _service.cardDetails(_cardId)
            .enqueue(object : Callback<CardsDetails> {
                override fun onResponse(call: Call<CardsDetails>, response: Response<CardsDetails>) {
                    if (response.isSuccessful) {
                        val card = response.body()!!.data[0]
                        bind(card)
                        Log.i("Goofy", card.toString())
                    }
                }

                override fun onFailure(call: Call<CardsDetails>, t: Throwable) {
                    Log.e(TAG, "Network Exception")
                }
            })
    }

    private fun bind(card: CardsDetails.Card) {
        val _image = findViewById<ImageView>(R.id.imageView)
        Glide.with(_image.context)
            .load(card.images.large)
            .into(_image)
    }

}