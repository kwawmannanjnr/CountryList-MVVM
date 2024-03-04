package com.kwawannan.countrylistmvvm.view.fragment

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwawannan.countrylistmvvm.Network.RetrofitServiceProvider.isNetworkAvailable
import com.kwawannan.countrylistmvvm.Network.repository.CountriesRepository
import com.kwawannan.countrylistmvvm.R
import com.kwawannan.countrylistmvvm.view.adapter.CountriesAdapter
import com.kwawannan.countrylistmvvm.viewmodel.CountriesViewModel
import com.kwawannan.countrylistmvvm.viewmodel.CountriesViewModelFactory

class CountriesFragment : Fragment() {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var countriesAdapter: CountriesAdapter
    val context = Application()

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
        initViewModel(requireActivity().application, repository = repository)

    }

    fun initViewModel(context: Application, repository: CountriesRepository){
        val viewModelFactory = CountriesViewModelFactory(context ,repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountriesViewModel::class.java)
        viewModel.countries.observe(viewLifecycleOwner, { countries ->
            countriesAdapter.submitList(countries ?: listOf())
        })
        if (isNetworkAvailable(requireContext())) {
            viewModel.fetchCountries()
        } else {
//            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            // Display the error message, for example, using a Toast:
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.countries.observe(viewLifecycleOwner) { countries ->
            countriesAdapter.submitList(countries)
        }

        if (isNetworkAvailable(requireActivity().application)) {
            viewModel.fetchCountries()
        } else {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }
}

