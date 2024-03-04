package com.kwawannan.countrylistmvvm.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kwawannan.countrylistmvvm.Network.model.Country
import com.kwawannan.countrylistmvvm.databinding.CountryItemBinding

class CountriesAdapter : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
    }

    class CountryViewHolder(private var binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.nameTextView.text = "${country.name}, ${country.region}"
            binding.codeTextView.text = country.code
            binding.capitalTextView.text = country.capital
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }
}
