package com.iothar.android.api.model

data class CardsDetails(
    val data: Card
) {
    companion object {
        const val DEFAULT_FLAVOR = "No description"
        const val DOLLAR = "$"
    }

    data class Card(
        val name: String,
        val number: String,
        val rarity: String,
        val flavorText: String,
        val images: Images,
        val cardmarket: CardMarket
    ) {
        data class Images(
            val large: String,
        )
        data class CardMarket(
            val updatedAt: String,
            val prices: Prices
        ) {
            data class Prices(
                val averageSellPrice: Double,
                val lowPrice: Double,
                val trendPrice: Double
            )
        }
    }
}