package com.wimobile.wmcountriesapp.data.network

import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET


interface CountriesApiClient {

    @GET(BuildConfig.GET_ALL_COUNTRIES)
    suspend fun getAllCountries(): Response<List<CountryModel>>

}