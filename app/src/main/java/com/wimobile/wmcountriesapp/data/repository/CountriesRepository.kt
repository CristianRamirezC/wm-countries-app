package com.wimobile.wmcountriesapp.data.repository

import com.wimobile.wmcountriesapp.core.utils.HandleApi
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.NetworkResult
import com.wimobile.wmcountriesapp.data.network.CountriesApiService
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apiService: CountriesApiService
){

    suspend fun getAllCountriesAPI(): NetworkResult<List<CountryModel>> {
        return HandleApi.handleGetApi {
            apiService.getAllCountries()
        }
    }

    suspend fun getAllCountriesDDBB(): List<CountryModel> {
        return listOf()
    }

}