package com.example.arinspectexercise.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.model.network.Resource
import com.example.arinspectexercise.repository.FactsRepository
import com.example.arinspectexercise.annotation.OpenForTesting

@OpenForTesting
class FactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FactsRepository = FactsRepository(application)
    private var forceRefresh = MutableLiveData<Boolean>()
    private val data: LiveData<Resource<Facts>> = Transformations.switchMap(forceRefresh) {
        repository.getFacts(forceRefresh.value ?: false)
    }

    init {
        refresh(true)
    }

    /**
     * Return facts data to observe on the UI.
     */
    fun getFacts() = data

    fun refresh(setForceRefresh: Boolean) {
        forceRefresh.value = setForceRefresh
    }
}