package com.example.arinspectexercise.network

import com.example.arinspectexercise.LiveDataCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class ApiClient {

    private val baseUrl = "https://dl.dropboxusercontent.com"

    val api: API

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

        api = retrofit.create(API::class.java)
    }
}