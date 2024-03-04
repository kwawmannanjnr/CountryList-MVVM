package com.kwawannan.countrylistmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.Country
import com.kwawannan.countrylistmvvm.Network.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    fun fetchCountries() {
        viewModelScope.launch {
            val countryList = repository.getCountries()
            _countries.postValue(countryList)
        }
    }
}
