package com.kwawannan.countrylistmvvm.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kwawannan.countrylistmvvm.Network.model.Countries
import com.kwawannan.countrylistmvvm.Network.model.Country
import com.kwawannan.countrylistmvvm.R

class CountriesAdapter(private val countries : Countries): RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(itemView)    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int = countries.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val flagImageView: ImageView = itemView.findViewById(R.id.flagImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val capitalTextView: TextView = itemView.findViewById(R.id.capitalTextView)

        fun bind(country: Country) {
             // I used Glide to load the flag image from URL

            Glide.with(itemView.context)
                .load(country.flag)
                .into(flagImageView)
            nameTextView.text = country.name
            capitalTextView.text = country.capital
        }
    }
}