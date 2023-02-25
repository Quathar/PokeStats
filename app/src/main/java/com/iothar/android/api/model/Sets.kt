package com.iothar.android.api.model

import java.net.URI

data class Sets(
    val id: String,
    val name: String,
    val total: Int,
    val images: Images
) {
data class Images(
    val symbol: URI,
    val logo: URI
)
}