package com.kwawannan.countrylistmvvm.Network

import com.kwawannan.countrylistmvvm.Network.endpoint.CountriesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitServiceProvider {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/peymano-wmt/") // base URL
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val countriesApi: CountriesApi by lazy {
        retrofit.create(CountriesApi::class.java)
    }
}
