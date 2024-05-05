package com.wimobile.wmcountriesapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wimobile.wmcountriesapp.R
import com.wimobile.wmcountriesapp.domain.model.CountryDomain

class CountryAdapter(private val countryList: List<CountryDomain>) :
    RecyclerView.Adapter<CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(layoutInflater.inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int = countryList.size


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countryList.elementAt(position)
        holder.render(item)
    }
}