package com.example.arinspectexercise.repository

import android.arch.lifecycle.LiveData
import com.example.arinspectexercise.network.ApiClient
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.arinspectexercise.model.Facts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FactsRepository {

    private var api = ApiClient().api

    fun getFacts(): LiveData<Facts> {
        val data = MutableLiveData<Facts>()
        api.getdata().enqueue(object : Callback<Facts> {
            override fun onResponse(call: Call<Facts>, response: Response<Facts>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<Facts>, t: Throwable) {
                Log.d("api error", t.message)
            }
        })
        return data
    }
}