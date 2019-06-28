package com.example.arinspectexercise.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.repository.FactsRepository


class FactsViewModel : ViewModel() {

    private val repository: FactsRepository = FactsRepository()
    private val reloadTrigger = MutableLiveData<Boolean>()

    private val data: LiveData<Facts> = Transformations.switchMap(reloadTrigger) {
        repository.getFacts()
    }

    init {
        refresh()
    }

    fun getFacts(): LiveData<Facts> = data

    fun refresh() {
        reloadTrigger.value = true
    }
}