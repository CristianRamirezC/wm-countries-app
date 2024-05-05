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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryByFifaUseCase: GetCountryByFifaUseCase,
    private val getCountriesByNameUseCase: GetCountriesByNameUseCase
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

    private var _searchBarLiveData: MutableLiveData<String> = MutableLiveData()
    val searchBarLiveData: LiveData<String> = _searchBarLiveData

    private var _isLoginButtonEnableLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoginButtonEnableLiveData: LiveData<Boolean> = _isLoginButtonEnableLiveData

    private var _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData

    fun onSearchBarChanged(searchBarString: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchBarLiveData.postValue(searchBarString ?: "")
                val barString: String = searchBarString ?: ""
                val countriesByNameList: List<CountryDomain> = getCountriesByNameUseCase(barString)
                if (countriesByNameList.isNotEmpty()) {
                    _countrySearchResultLiveData.postValue(countriesByNameList)
                }
            } catch (e: Exception) {
                Log.e("onSearchBarChangedException", e.stackTraceToString())
            }
        }

    }

    fun onSearchButtonPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchBarString: String = _searchBarLiveData.value ?: ""
                val countriesSearchResult: List<CountryDomain> = if (searchBarString.isBlank()) {
                    getAllCountriesUseCase()
                } else {
                    getCountriesByNameUseCase(searchBarString)
                }
                _countrySearchResultLiveData.postValue(countriesSearchResult)
            } catch (e: Exception) {
                Log.e("onSearchButtonPressedException", e.stackTraceToString())
            }
        }
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countries: List<CountryDomain> = getAllCountriesUseCase()
                Log.i("AllCountries", "$countries")
                _countriesListLivaData.postValue(countries)
            } catch (e: Exception) {
                Log.e("getAllCountriesException", e.stackTraceToString())
            }
        }
    }

    fun getCountryByFifa(fifa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val country: CountryDomain? = getCountryByFifaUseCase(fifa)
                if (country != null) {
                    _countryToDisplayDetailLiveData.postValue(country!!)
                }
            } catch (e: Exception) {
                Log.e("getCountryByFifaException", e.stackTraceToString())
            }
        }
    }

    fun getBorderCountriesInformation(borders: List<String>) {
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