package com.kwawannan.countrylistmvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwawannan.countrylistmvvm.Network.endpoint.CountriesApi
import com.kwawannan.countrylistmvvm.Network.repository.CountriesRepository
import com.kwawannan.countrylistmvvm.R
import com.kwawannan.countrylistmvvm.view.adapter.CountriesAdapter
import com.kwawannan.countrylistmvvm.viewmodel.CountriesViewModel
import com.kwawannan.countrylistmvvm.viewmodel.CountriesViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountriesFragment : Fragment() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var countriesAdapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countriesAdapter = CountriesAdapter()
        view.findViewById<RecyclerView>(R.id.countriesRecyclerView).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
            countriesAdapter = CountriesAdapter()
            view.findViewById<RecyclerView>(R.id.countriesRecyclerView).apply {
                layoutManager = LinearLayoutManager(context)
                adapter = countriesAdapter
            }
        }

        val repository = CountriesRepository()
        initViewModel(repository = repository)

    }

    fun initViewModel(repository: CountriesRepository){
        val viewModelFactory = CountriesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)

        viewModel.countries.observe(viewLifecycleOwner, { countries ->
            countriesAdapter.submitList(countries ?: listOf())
        })
        viewModel.fetchCountries()
    }
}

