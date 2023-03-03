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
        val cardmarket: Cardmarket
    ) {
        data class Images(
            val large: String,
        )
        data class Cardmarket(
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