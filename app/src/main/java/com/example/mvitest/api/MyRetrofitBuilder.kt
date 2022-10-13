package com.example.mvitest.api

import leakcanary.internal.InternalLeakCanary.application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val baseUrl = "https://open-api.xyz/"

//singleton
object MyRetrofitBuilder {

    val retrofitBuilder : Retrofit.Builder by lazy {    //by lazy to initialize only once
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiService : ApiService by lazy {
        retrofitBuilder.build()
            .create(ApiService::class.java)
    }

}