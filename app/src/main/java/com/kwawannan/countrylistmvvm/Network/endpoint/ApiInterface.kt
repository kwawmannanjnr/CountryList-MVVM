package com.kwawannan.countrylistmvvm.Network.endpoint

import com.kwawannan.countrylistmvvm.Network.model.Country
import retrofit2.Response
import retrofit2.http.GET


class ApiInterface {
}
interface CountriesApi {
    @GET("32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<List<Country>>
}