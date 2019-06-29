package com.example.arinspectexercise.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import com.example.arinspectexercise.network.ApiClient
import android.arch.persistence.room.Room
import com.example.arinspectexercise.AppExecutors
import com.example.arinspectexercise.NetworkBoundResource
import com.example.arinspectexercise.database.FactsDatabase
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.model.network.Resource

class FactsRepository(application: Application) {

    private val api = ApiClient().api
    private val factsDatabase: FactsDatabase =
        Room.databaseBuilder(application, FactsDatabase::class.java, "facts-db").build()
    private val factsDao = factsDatabase.factsDao()
    private val appExecutors: AppExecutors = AppExecutors()

    init {
    }

    /**
     * Fetch the news articles from database if exist else fetch from web
     * and persist them in the database
     */
    fun getFacts(forceRefresh: Boolean): LiveData<Resource<Facts>> {
        return object : NetworkBoundResource<Facts, Facts>(appExecutors) {
            override fun saveCallResult(item: Facts) {
                factsDatabase.runInTransaction {
                    factsDao.deleteFacts()
                    factsDao.insertFacts(item)
                }
            }

            override fun shouldFetch(data: Facts?) = forceRefresh || data == null

            override fun loadFromDb() = factsDao.getFacts()

            override fun createCall() = api.getdata()
        }.asLiveData()
    }

    fun refresh() {

    }
}