package com.kwawannan.countrylistmvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.Country
import com.kwawannan.countrylistmvvm.Network.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(application: Application, private val repository: CountriesRepository) : AndroidViewModel(application)  {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries
    private val _error = MutableLiveData<String>() // Private mutable LiveData for errors.
    val error: LiveData<String> = _error // Public immutable LiveData for the UI to observe.

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countryList = repository.getCountries()
                if (countryList != null) {
                    _countries.postValue(countryList)
                } else {
                    _error.postValue("Failed to fetch countries") // Set an error message
                }
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An unknown error occurred. Maybe there's no internet connection") // Set the error message from the exception
            }
        }
    }
}
