package com.honeykoders.bankodemia.network

import android.content.Context
import com.honeykoders.bankodemia.common.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
           // .addInterceptor(AuthInterceptor(context = context))
            .build()
        return Retrofit.Builder()
            .baseUrl(" https://bankodemia.kodemia.mx")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    class AuthInterceptor(context: Context) : Interceptor {
        val utils: Utils = Utils()

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()

            requestBuilder.addHeader(
                "Authorization",
                "Bearer ${utils.getSharedPreferencesByName("token")}"
            )

            return chain.proceed(requestBuilder.build())
        }
    }


}