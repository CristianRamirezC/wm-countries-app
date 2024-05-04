package com.wimobile.wmcountriesapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.useCases.GetAllCountriesUseCase
import com.wimobile.wmcountriesapp.domain.useCases.GetCountryByFifaUseCase
import com.wimobile.wmcountriesapp.domain.useCases.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountryByFifaUseCase: GetCountryByFifaUseCase,
    private val getCountryByNameUseCase: GetCountryByNameUseCase
) : ViewModel() {


    private var _countriesList: MutableLiveData<List<CountryDomain>> = MutableLiveData()
    val countriesList: LiveData<List<CountryDomain>> = _countriesList

    private var _borderCountries: MutableLiveData<List<CountryDomain>> = MutableLiveData()
    val borderCountries: LiveData<List<CountryDomain>> = _borderCountries

    private var _countryToDisplayDetail: MutableLiveData<CountryDomain> = MutableLiveData()
    val countryToDisplayDetail: LiveData<CountryDomain> = _countryToDisplayDetail

    private var _countrySearchResult: MutableLiveData<CountryDomain> = MutableLiveData()
    val countrySearchResult: LiveData<CountryDomain> = _countrySearchResult

    private var _searchBar: MutableLiveData<String> = MutableLiveData()
    val searchBar: LiveData<String> = _searchBar

    private var _isLoginButtonEnable: MutableLiveData<Boolean> = MutableLiveData()
    val isLoginButtonEnable: LiveData<Boolean> = _isLoginButtonEnable

    private var _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onSearchBarChanged(searchBarString: String) {
        _searchBar.value = searchBarString
    }

    fun onSearchButtonPressed() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.postValue(true)
                val searchBarString: String = _searchBar.value ?: ""
                val country: CountryDomain = getCountryByNameUseCase(searchBarString)
                _countrySearchResult.postValue(country)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                Log.e("onSearchButtonPressedException", e.stackTraceToString())
            }
        }
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val countries: List<CountryDomain> = getAllCountriesUseCase()
                _countriesList.postValue(countries)
            } catch (e: Exception) {
                Log.e("getAllCountriesException", e.stackTraceToString())
            }
        }
    }

    fun getCountryByFifa(fifa: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val country: CountryDomain = getCountryByFifaUseCase(fifa)
                _countryToDisplayDetail.postValue(country)
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
                    countries.add(getCountryByFifaUseCase(it))
                }
                _borderCountries.postValue(countries)
            } catch (e: Exception) {
                Log.e("getBorderCountriesInformation", e.stackTraceToString())
            }
        }
    }


}