package com.iothar.android.api.model

data class CardsDetails(
    val data: Card
) {
    data class Card(
        val name: String,
        val number: String,
        val rarity: String,
        val flavorText: String,
        val images: Images,
        val cardMarket: CardMarket
    ) {
        data class Images(
            val large: String,
        )

        data class CardMarket(
            val url: String,
            val updatedAt: String,
            val prices: Prices
        ) {
            data class Prices(
                val averageSellPrice: Int,
                val lowPrice: Int,
                val trendPrice: Int
            )
        }
    }
}