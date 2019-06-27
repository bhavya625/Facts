package com.example.arinspectexercise.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.arinspectexercise.model.Facts
import com.example.arinspectexercise.repository.FactsRepository


class FactsViewModel : ViewModel() {

    private val repository: FactsRepository = FactsRepository()
    val data: LiveData<Facts> = repository.getFacts()
}