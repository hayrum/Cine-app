package com.example.cineapp.services.instance

import android.content.Context
import com.example.cineapp.BuildConfig
import com.example.cineapp.services.ApiServices
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Hayrum Vega
 * @since 09/10/2022
 * @description This abstract class is for create new instance of use the Services Rest API.
 */
abstract class ApiInstance : ApiServices {

    companion object {
        private var sInstance: ApiServices? = null
        private lateinit var mCache: Cache
        private lateinit var mClient: OkHttpClient
        private var mBaseUrl: String = BuildConfig.BASE_URL_API
        private val cacheSize = (5 * 1024 * 1024).toLong()

        // Create new instance of the service.
        fun getInstance(context: Context): ApiServices {
            if (sInstance != null) {
                return sInstance!!
            }

            sInstance = initApi(context)
            return sInstance!!
        }


        private fun initApi(context: Context): ApiServices {
            mCache = Cache(context.cacheDir, cacheSize)
            val builder = OkHttpClient.Builder()
            builder.cache(mCache)
            builder.connectTimeout(80, TimeUnit.SECONDS)
            builder.readTimeout(80, TimeUnit.SECONDS)
            builder.writeTimeout(80, TimeUnit.SECONDS)
            builder.interceptors().add(
                AuthenticationInterceptor(
                    context
                )
            )

            mClient = builder.build()
            return Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(mClient)
                .build().create(ApiServices::class.java)
        }

    }
}