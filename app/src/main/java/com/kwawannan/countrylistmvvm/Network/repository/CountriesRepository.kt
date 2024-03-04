package com.kwawannan.countrylistmvvm.Network.repository

import com.kwawannan.countrylistmvvm.Network.RetrofitServiceProvider
import com.kwawannan.countrylistmvvm.Network.endpoint.CountriesApi
import com.kwawannan.countrylistmvvm.Network.model.Country

class CountriesRepository {
    private val api: CountriesApi = RetrofitServiceProvider.countriesApi

    suspend fun getCountries(): List<Country>? {
        val response = api.getCountries()
        return if (response.isSuccessful) response.body() else null
    }
}
