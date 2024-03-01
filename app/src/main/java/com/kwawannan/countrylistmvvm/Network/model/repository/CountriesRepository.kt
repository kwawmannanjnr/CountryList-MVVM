package com.kwawannan.countrylistmvvm.Network.model.repository

import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.endpoint.CountriesApi

class CountriesRepository(private val api:CountriesApi) {
    suspend fun  getCountries(): Countries?{
        val response = api.getCountries()
        return  if (response.isSuccessful)
            response.body()else null
    }
}