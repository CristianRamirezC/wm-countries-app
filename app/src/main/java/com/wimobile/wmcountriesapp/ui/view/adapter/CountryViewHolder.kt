package com.wimobile.wmcountriesapp.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.wimobile.wmcountriesapp.databinding.ItemCountryBinding
import com.wimobile.wmcountriesapp.domain.model.CountryDomain

class CountryViewHolder(val view: View) : ViewHolder(view) {

    private val binding = ItemCountryBinding.bind(view)

    private val countryName = binding.tvCountryName
    private val countryFlag = binding.ivCountryFlag
    private val countryCapital = binding.tvCountryCapital

    fun render(country: CountryDomain) {
        countryName.text = country.name?.official ?: ""

        countryCapital.text = if (country.capital.isNotEmpty()) country.capital.first()
        else "No capital"

        val flagUrl: String = country.flags?.png ?: ""
        Glide.with(countryFlag.context).load(flagUrl).into(countryFlag)
    }
}