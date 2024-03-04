package com.kwawannan.countrylistmvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.Country
import com.kwawannan.countrylistmvvm.R
import com.kwawannan.countrylistmvvm.databinding.CountryItemBinding

//class CountriesAdapter  : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DIFF_CALLBACK)  {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
//        return CountryViewHolder(itemView)    }
//
//    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
//        val country = countries[position]
//        holder.bind(country)
//    }
//
//    override fun getItemCount(): Int = countries.size
//
//    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        private val flagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
//        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
//        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)
//
//        fun bind(country: Country) {
//             // I used Glide to load the flag image from URL
//
//            Glide.with(itemView.context)
//                .load(country.flag)
//                .into(flagImageView)
//            nameTextView.text = country.name
//            capitalTextView.text = country.capital
//        }
//    }
//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
//            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem.name == newItem.name
//            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean = oldItem == newItem
//        }
//    }
//}
class CountriesAdapter : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(DiffCallback) {

    class CountryViewHolder(private var binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.nameTextView.text = country.name
//            binding.codeTextView.text = country.code
            binding.capitalTextView.text = country.capital
            // Load image using Glide or another library
            Glide.with(itemView.context)
                .load(country.flag)
                .into(binding.flagImageView)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
    }
}
