package com.wimobile.wmcountriesapp.data.repository

import com.wimobile.wmcountriesapp.core.utils.HandleApi
import com.wimobile.wmcountriesapp.data.database.dao.CountriesDao
import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.database.entities.toEntity
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.NetworkResult
import com.wimobile.wmcountriesapp.data.network.CountriesApiService
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.toDomain
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apiService: CountriesApiService,
    private val countriesDao: CountriesDao
) {

    suspend fun getAllCountriesAPI(): NetworkResult<List<CountryModel>> {
        return HandleApi.handleGetApi {
            apiService.getAllCountries()
        }
    }

    suspend fun getAllCountriesDDBB(): List<CountryDomain> {
        val countriesEntity: List<CountryEntity> = countriesDao.getAllCountries()
        return countriesEntity.map { it.toDomain() }
    }

    suspend fun getCountryByFifa(fifa: String): CountryDomain {
        val countryEntity = countriesDao.getCountryByFifa(fifa)
        return countryEntity.toDomain()
    }

    suspend fun storeAllCountriesDDBB(countries: List<CountryDomain>) {
        val countriesEntity: List<CountryEntity> = countries.map { it.toEntity() }
        countriesDao.insertAllCountries(countriesEntity)
    }


}