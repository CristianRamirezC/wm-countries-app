package com.wimobile.wmcountriesapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wimobile.wmcountriesapp.databinding.FragmentCountryDetailsBinding
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.ui.view.adapter.CountryAdapter
import com.wimobile.wmcountriesapp.ui.viewModel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentCountryDetailsBinding
    private val args: CountryDetailsFragmentArgs by navArgs()
    private val countriesViewModel: CountriesViewModel by viewModels()

    private lateinit var officialNameArg: String

    private lateinit var countryFlag: ImageView
    private lateinit var countryName: TextView
    private lateinit var countryCapital: TextView
    private lateinit var countryArea: TextView
    private lateinit var countryPopulation: TextView
    private lateinit var countryRegion: TextView
    private lateinit var countrySubregion: TextView
    private lateinit var countryRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewBinding()
        setPopBackStack()
        officialNameArg = args.countryName
        initRecyclerView()
        setUpLiveDataObservers()
        countriesViewModel.getCountryByOfficialName(officialNameArg)


    }

    private fun setUpLiveDataObservers() {
        countriesViewModel.countryToDisplayDetailLiveData.observe(viewLifecycleOwner) {
            setUpUI(it)
            countriesViewModel.getBorderCountries(it.borders)
        }

        countriesViewModel.borderCountriesLiveData.observe(viewLifecycleOwner) {
            countryRecyclerView.adapter = CountryAdapter(it){}
        }

    }

    private fun setUpViewBinding() {
        countryFlag = _binding.ivCountryFlag
        countryName = _binding.tvCountryName
        countryCapital = _binding.tvCountryCapital
        countryArea = _binding.tvArea
        countryPopulation = _binding.tvPopulation
        countryRegion = _binding.tvRegion
        countrySubregion = _binding.tvSubregion
        countryRecyclerView = _binding.recyclerCountriesRV
    }

    private fun setPopBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })
    }

    private fun setUpUI(country: CountryDomain) {
        try {
            countryName.text = country.name?.official ?: ""

            val countryFlagUrl = country.flags?.png ?: ""
            Glide.with(countryFlag.context).load(countryFlagUrl).into(countryFlag)

            countryCapital.text = if (country.capital.isNotEmpty()) country.capital.first()
            else ""

            countryArea.text = if (country.area != null) country.area.toString()
            else "0.0"

            countryPopulation.text = if (country.population != null) country.population.toString()
            else "0"

            countryRegion.text = country.region
            countrySubregion.text = country.subregion

        } catch (e: Exception) {
            Log.e("setUpUIException", e.stackTraceToString())
        }
    }

    private fun initRecyclerView() {
        countryRecyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext)
        countryRecyclerView.adapter = CountryAdapter(listOf()) {}
    }


}