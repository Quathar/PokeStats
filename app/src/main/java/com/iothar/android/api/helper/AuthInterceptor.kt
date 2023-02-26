package com.iothar.android.api.helper

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val authToken: String) : Interceptor {

    // <<-METHOD->>
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", authToken)
            .build()

        return chain.proceed(request)
    }
}