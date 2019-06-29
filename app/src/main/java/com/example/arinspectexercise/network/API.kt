package com.example.arinspectexercise.network

import android.arch.lifecycle.LiveData
import com.example.arinspectexercise.model.Facts
import retrofit2.http.GET


interface API {

    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getdata(): LiveData<ApiResponse<Facts>>
}