package com.iothar.android.api.model

data class Cards(
    val id: String,
    val images: Images
) {
    data class Images(
        val small: String,
    )
}