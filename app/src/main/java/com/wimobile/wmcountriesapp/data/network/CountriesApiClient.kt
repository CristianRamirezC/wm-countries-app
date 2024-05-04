package com.wimobile.wmcountriesapp.data.network

import com.wimobile.wmcountriesapp.BuildConfig
import com.wimobile.wmcountriesapp.data.model.CountryModel
import retrofit2.Response
import retrofit2.http.GET


interface CountriesApiClient {

    @GET(BuildConfig.BASE_URL)
    suspend fun getAllCountries(): Response<List<CountryModel>>

}