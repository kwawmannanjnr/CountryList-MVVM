package com.kwawannan.countrylistmvvm.Network.model

import com.kwawannan.countrylistmvvm.Network.model.Currency
import com.kwawannan.countrylistmvvm.Network.model.Language

data class Country(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)
