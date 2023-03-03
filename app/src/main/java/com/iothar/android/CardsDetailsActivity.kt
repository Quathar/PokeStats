package com.iothar.android

import android.animation.LayoutTransition
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.iothar.android.api.helper.PokemonAPI
import com.iothar.android.api.model.CardsDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardsDetailsActivity : AppCompatActivity() {

    // <<-CONSTANTS->>
    companion object {
        private val TAG: String = CardsDetailsActivity::class.java.name
        const val CARD_ID_KEY = "CARD_ID"
    }

    // <<-FIELDS->>
    private lateinit var _cardView: CardView
    private lateinit var _cardLayout: ConstraintLayout
    private lateinit var _cardImage: ImageView
    private lateinit var _cardInfoDetails: TextView
    private lateinit var _cardMarket: TextView
    private lateinit var _cardMarketDetails: TextView
    private lateinit var _cardId: String
    private val _service = PokemonAPI.pokemonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_details)
        _cardView = findViewById(R.id.card_view)
        _cardLayout = findViewById(R.id.card_layout)
        _cardImage = findViewById(R.id.card_image_large)
        _cardInfoDetails = findViewById(R.id.card_info_details)
        _cardMarket = findViewById(R.id.card_market)
        _cardMarketDetails = findViewById(R.id.card_market_details)

        _cardLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        _cardView.setOnClickListener {
            val isVisible = if (_cardInfoDetails.visibility == View.GONE) View.VISIBLE else View.GONE
            TransitionManager.beginDelayedTransition(_cardLayout, AutoTransition())
            _cardInfoDetails.visibility = isVisible
            _cardMarket.visibility = isVisible
            _cardMarketDetails.visibility = isVisible
        }

        _cardId = intent.getStringExtra(CARD_ID_KEY).toString()

        loadCard()
    }

    private fun loadCard() {
        _service.cardDetails(_cardId)
            .enqueue(object : Callback<CardsDetails> {
                override fun onResponse(call: Call<CardsDetails>, response: Response<CardsDetails>) {
                    if (response.isSuccessful)
                        bind(response.body()!!.data)
                }

                override fun onFailure(call: Call<CardsDetails>, t: Throwable) {
                    Log.e(TAG, "Network Exception")
                }
            })
    }

    private fun bind(card: CardsDetails.Card) {
        Glide.with(_cardImage.context)
            .load(card.images.large)
            .into(_cardImage)
        _cardInfoDetails.text = getString(
            R.string.card_info_details,
            card.flavorText,
            card.name,
            card.number,
            card.rarity
        )
        _cardMarketDetails.text = getString(
            R.string.card_market_details,
            card.cardmarket.updatedAt,
            card.cardmarket.prices.averageSellPrice,
            card.cardmarket.prices.lowPrice,
            card.cardmarket.prices.trendPrice
        )
    }

}