package com.wimobile.wmcountriesapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.useCases.GetAllCountriesUseCase
import com.wimobile.wmcountriesapp.domain.useCases.GetCountryByFifaUseCase
import com.wimobile.wmcountriesapp.domain.useCases.GetCountriesByNameUseCase
import com.wimobile.wmcountriesapp.domain.useCases.GetCountryByOfficialNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryByFifaUseCase: GetCountryByFifaUseCase,
    private val getCountriesByNameUseCase: GetCountriesByNameUseCase,
    private val getCountryByOfficialNameUseCase: GetCountryByOfficialNameUseCase
) : ViewModel() {

    private var _countriesListLivaData: MutableLiveData<List<CountryDomain>> = MutableLiveData()
    val countriesListLiveData: LiveData<List<CountryDomain>> = _countriesListLivaData

    private var _borderCountriesLiveData: MutableLiveData<List<CountryDomain>> = MutableLiveData()
    val borderCountriesLiveData: LiveData<List<CountryDomain>> = _borderCountriesLiveData

    private var _countryToDisplayDetailLiveData: MutableLiveData<CountryDomain> = MutableLiveData()
    val countryToDisplayDetailLiveData: LiveData<CountryDomain> = _countryToDisplayDetailLiveData

    private var _countrySearchResultLiveData: MutableLiveData<List<CountryDomain>> =
        MutableLiveData()
    val countrySearchResultLiveData: LiveData<List<CountryDomain>> = _countrySearchResultLiveData

    private var _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    fun onSearchBarChanged(searchBarString: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (searchBarString.isNullOrBlank()) {
                    getAllCountries()
                }
            } catch (e: Exception) {
                Log.e("onSearchBarChangedException", e.stackTraceToString())
            }
        }
    }

    fun onSearchButtonPressed(query: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countries: List<CountryDomain>? = getCountriesByNameUseCase(query ?: "")
                if (countries.isNullOrEmpty()) {
                    _countrySearchResultLiveData.postValue(listOf())
                } else {
                    _countrySearchResultLiveData.postValue(countries)
                }
            } catch (e: Exception) {
                Log.e("onSearchButtonPressedException", e.stackTraceToString())
            }
        }
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.i("getCountriesExecuted", "getAllCountries executed")
                _isLoadingLiveData.postValue(true)
                val countries: List<CountryDomain> = getAllCountriesUseCase()
                _countriesListLivaData.postValue(countries)
                _isLoadingLiveData.postValue(false)
            } catch (e: Exception) {
                Log.e("getAllCountriesException", e.stackTraceToString())
            }
        }
    }

    fun getCountryByOfficialName(officialName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val country = getCountryByOfficialNameUseCase(officialName)
                _countryToDisplayDetailLiveData.postValue(country)

            } catch (e: Exception) {
                Log.e("getCountryByOfficialNameException", e.stackTraceToString())
            }
        }
    }

    fun getBorderCountries(borders: List<String>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countries: MutableList<CountryDomain> = mutableListOf()
                borders.forEach {
                    val country = getCountryByFifaUseCase(it)
                    if (country != null) {
                        countries.add(country)
                    }
                }
                _borderCountriesLiveData.postValue(countries)
            } catch (e: Exception) {
                Log.e("getBorderCountriesInformation", e.stackTraceToString())
            }
        }
    }


}