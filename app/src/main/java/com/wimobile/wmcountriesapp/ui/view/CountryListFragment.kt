package com.wimobile.wmcountriesapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wimobile.wmcountriesapp.databinding.FragmentCountryListBinding
import com.wimobile.wmcountriesapp.ui.viewModel.CountriesViewModel
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.wimobile.wmcountriesapp.ui.view.adapter.CountryAdapter
import androidx.recyclerview.widget.RecyclerView

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private lateinit var _binding: FragmentCountryListBinding

    private val countriesViewModel: CountriesViewModel by viewModels()

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        countriesViewModel.getAllCountries()

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView = _binding.searchBarSV
        recyclerView = _binding.recyclerCountriesRV

        initRecyclerView()
        setUpViews()
        setUpLiveDataObservers()

    }//end of onViewCreated

    private fun setUpViews() {

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("setOnQueryTextListener", "$newText")
                countriesViewModel.onSearchBarChanged(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
//                countriesViewModel.onSearchButtonPressed()
                searchView.clearFocus()
                return false
            }
        })
    }

    private fun setUpLiveDataObservers() {
        countriesViewModel.countrySearchResultLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = CountryAdapter(it)
        }

        countriesViewModel.countriesListLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = CountryAdapter(it)
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = CountryAdapter(listOf())
    }

}//end of CountryListFragment
