package com.iothar.android.api.helpers

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(authToken: String) : Interceptor {

    // <<-FIELD->>
    private val _authToken = authToken

    // <<-METHOD->>
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", _authToken)
            .build()

        return chain.proceed(request)
    }
}