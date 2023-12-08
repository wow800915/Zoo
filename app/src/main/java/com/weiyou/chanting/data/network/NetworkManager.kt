package com.weiyou.chanting.data.network

import com.google.gson.GsonBuilder
import com.weiyou.chanting.utils.Constants.BASE_URL_TAIPEI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_TAIPEI)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .client(initOkHttpClient())
            .build()
    }


    private fun initOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val build = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        return build.build()
    }

    fun <T> create(apiService: Class<T>): T {
        return retrofit.create(apiService)
    }

}

