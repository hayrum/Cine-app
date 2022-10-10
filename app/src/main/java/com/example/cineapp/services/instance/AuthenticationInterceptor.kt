package com.example.cineapp.services.instance

import android.content.Context
import com.example.cineapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Hayrum Vega
 * @since 09/10/2022
 * @description This class is for create a custom header of the request.
 */
class AuthenticationInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .header(
                "Authorization",
                String.format("Bearer %s", BuildConfig.ACCESS_TOKEN)) // Access token.
            .build()

        return chain.proceed(newRequest)
    }
}