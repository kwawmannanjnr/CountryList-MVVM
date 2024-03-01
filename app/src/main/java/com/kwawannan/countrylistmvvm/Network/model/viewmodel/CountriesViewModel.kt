package com.kwawannan.countrylistmvvm.Network.model.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.repository.CountriesRepository
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepository): ViewModel()  {
    private val _countries = MutableLiveData<Countries>()
    val countries: LiveData<Countries> = _countries

    fun fetchCountries(){
        viewModelScope.launch {
            val countryList = repository.getCountries()
            if (countryList !=null){
                _countries.postValue(countryList)
            }else{
                //Handle errors
            }
        }
    }
}