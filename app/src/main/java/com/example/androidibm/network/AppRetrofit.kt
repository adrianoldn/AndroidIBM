package com.example.testecathodev.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class AppRetrofit {

    companion object {
        val url_base = "http://5f5a8f24d44d640016169133.mockapi.io/api/"
    }

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.MINUTES)
        .readTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build();

    val retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(url_base)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun testeService(): AppService {
        return retrofit.create(AppService::class.java)
    }

}