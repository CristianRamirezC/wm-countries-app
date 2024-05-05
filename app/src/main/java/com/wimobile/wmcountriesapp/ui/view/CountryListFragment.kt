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

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private lateinit var _binding: FragmentCountryListBinding

    private val countriesViewModel: CountriesViewModel by viewModels()

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

        val searchView = _binding.searchBarSV
        val textViewTest = _binding.testTV

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("setOnQueryTextListener", "$newText")
                countriesViewModel.onSearchBarChanged(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                countriesViewModel.onSearchButtonPressed()
                return false
            }
        })

        countriesViewModel.countrySearchResult.observe(viewLifecycleOwner) {
            textViewTest.text = it.toString()
        }


    }//end of onViewCreated

}//end of CountryListFragment
