package com.wimobile.wmcountriesapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.wimobile.wmcountriesapp.databinding.FragmentCountryListBinding
import com.wimobile.wmcountriesapp.ui.viewModel.CountriesViewModel
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wimobile.wmcountriesapp.ui.view.adapter.CountryAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wimobile.wmcountriesapp.domain.model.CountryDomain

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    private lateinit var _binding: FragmentCountryListBinding

    private val countriesViewModel: CountriesViewModel by viewModels()

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var noMatchesFoundView: TextView
    private lateinit var shimmerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewBinding()
        initRecyclerView()
        setUpViews()
        setUpLiveDataObservers()

    }//end of onViewCreated

    override fun onResume() {
        super.onResume()
        searchView.setQuery("", false)
    }

    private fun setUpViewBinding() {
        searchView = _binding.searchBarSV
        recyclerView = _binding.recyclerCountriesRV
        noMatchesFoundView = _binding.tvNoMAtchesFound
        shimmerLayout = _binding.shimmerLayout
    }

    private fun setUpViews() {
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                countriesViewModel.onSearchBarChanged(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                countriesViewModel.onSearchButtonPressed(query)
                searchView.clearFocus()
                return false
            }
        })
        
    }

    private fun setUpLiveDataObservers() {
        countriesViewModel.countrySearchResultLiveData.observe(viewLifecycleOwner) { countriesFound ->
            setUpNoMatchesFoundVisibility(countriesFound)
            recyclerView.adapter = CountryAdapter(countriesFound) { country ->
                navigateToCountryDetail(country)
            }
        }

        countriesViewModel.countriesListLiveData.observe(viewLifecycleOwner) { initialCountries ->
            setUpNoMatchesFoundVisibility(initialCountries)
            recyclerView.adapter = CountryAdapter(initialCountries) {
                navigateToCountryDetail(it)
            }
        }

        countriesViewModel.isLoadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                shimmerLayout.visibility = View.VISIBLE
                recyclerView.visibility = View.INVISIBLE
            } else {
                shimmerLayout.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    private fun setUpNoMatchesFoundVisibility(countries: List<CountryDomain>?) {
        if (countries.isNullOrEmpty()) {
            noMatchesFoundView.visibility = View.VISIBLE
        } else {
            noMatchesFoundView.visibility = View.GONE
        }

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recyclerView.adapter = CountryAdapter(listOf()) {
            navigateToCountryDetail(it)
        }
    }

    private fun navigateToCountryDetail(officialName: String) {
        try {
            val direction: NavDirections =
                CountryListFragmentDirections
                    .actionCountryListFragmentToCountryDetailsFragment(officialName)

            val navController = findNavController()
            navController.navigate(direction)
        } catch (e: Exception) {
            Log.e("navigateToCountryDetail", e.stackTraceToString())
        }
    }

}//end of CountryListFragment
