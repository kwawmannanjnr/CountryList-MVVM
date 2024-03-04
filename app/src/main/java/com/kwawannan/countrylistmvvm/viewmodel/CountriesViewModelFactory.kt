package com.kwawannan.countrylistmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kwawannan.countrylistmvvm.Network.repository.CountriesRepository

class CountriesViewModelFactory(private val repository: CountriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
