package com.honeykoders.bankodemia.network

import android.content.Context
import com.honeykoders.bankodemia.common.Utils
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    val utils: Utils = Utils()
    val context = context

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        utils.initSharedPreferences(context)

        requestBuilder.addHeader(
            "Authorization",
            "Bearer ${utils.getSharedPreferencesByName("token")}"
        )
        return chain.proceed(requestBuilder.build())
    }
}