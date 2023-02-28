package com.iothar.android.api.model

data class Cards(
    val id: String,
    val name: String,
    val images: Images
) {
    class Images(
        val small: String,
        val large: String
    )
}