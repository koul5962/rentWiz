package com.rentwiz.app.core.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CoreInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        request.addHeader("Authorization","token")
        return chain.proceed(request.build())
    }
}