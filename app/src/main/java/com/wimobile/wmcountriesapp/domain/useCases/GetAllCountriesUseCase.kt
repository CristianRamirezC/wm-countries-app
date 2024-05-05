package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.NetworkResult
import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.toDomain
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository,
) {

    /** Get all countries: first try to get the info from db, if no info is found,
     * try to get the info from API, and then if there's issues getting data from API,
     * return an empty list
     * **/
    suspend operator fun invoke(): List<CountryDomain> {

        val countriesFromDB: List<CountryDomain> =
            countriesRepository.getAllCountriesDDBB().map { it.toDomain() }

        return countriesFromDB.ifEmpty {
            val apiResponse: NetworkResult<List<CountryModel>> =
                countriesRepository.getAllCountriesAPI()

            when (apiResponse) {
                is NetworkResult.ApiSuccess -> {
                    val countriesList: List<CountryDomain> = apiResponse.data.map { it.toDomain() }

                    //Store countries from API in the database
                    countriesRepository.storeAllCountriesDDBB(countriesList)
                    countriesList
                }

                is NetworkResult.ApiError -> listOf()

                is NetworkResult.ApiException -> listOf()
            }
        }
    }
}
