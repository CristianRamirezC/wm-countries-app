package com.wimobile.wmcountriesapp.data.network

import com.wimobile.wmcountriesapp.data.model.CountryModel
import retrofit2.Response
import javax.inject.Inject

class CountriesApiService @Inject constructor(
    private val apiClient: CountriesApiClient
) {
    suspend fun getAllCountries(): Response<List<CountryModel>> {
        return apiClient.getAllCountries()
    }
}