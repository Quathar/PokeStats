package com.iothar.android.api.model

data class Sets(
    val id: String,
    val name: String,
    val total: Int,
    val images: Images
) {
    data class Images(
        val symbol: String,
        val logo: String
    )
}