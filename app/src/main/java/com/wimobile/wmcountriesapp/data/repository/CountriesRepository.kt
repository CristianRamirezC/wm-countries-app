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

    /** Fetch countries data from API **/
    suspend fun getAllCountriesAPI(): NetworkResult<List<CountryModel>> {
        return HandleApi.handleGetApi {
            apiService.getAllCountries()
        }
    }

    /** fetch countries data from database **/
    suspend fun getAllCountriesDDBB(): List<CountryEntity> {
        return countriesDao.getAllCountries().ifEmpty {
            listOf()
        }
    }

    /** fetch information fo country by fifa.
     * fifa example: CANADA=CAN
     * **/
    suspend fun getCountryByFifa(fifa: String): CountryDomain? {
        val countryEntity = countriesDao.getCountryByFifa(fifa)
        return countryEntity?.toDomain()
    }

    /** store countries on database **/
    suspend fun storeAllCountriesDDBB(countries: List<CountryDomain>) {
        val countriesListEntity: List<CountryEntity> = countries.map { it.toEntity() }
        countriesDao.insertAllCountries(countriesListEntity)
    }

    suspend fun getCountriesByName(name: String): List<CountryDomain> {
        val countryList: List<CountryDomain> =
            countriesDao.getCountriesByName(name).map { it.toDomain() }

        return countryList.filter {
            it.name?.common?.contains(name, true) ?: false
        }
    }


}