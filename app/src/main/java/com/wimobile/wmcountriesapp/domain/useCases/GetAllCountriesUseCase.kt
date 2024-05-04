package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.NetworkResult
import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.toDomain
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository,
) {

    /** Get all countries from API, if successful, store the result in database
     * if not successful try to fetch the data from database **/
    suspend operator fun invoke(): List<CountryDomain> {

        val apiResponse: NetworkResult<List<CountryModel>> =
            countriesRepository.getAllCountriesAPI()

        return when (apiResponse) {
            is NetworkResult.ApiSuccess -> {
                val countriesDomain: List<CountryDomain> =
                    apiResponse.data.map { it.toDomain() }
                //Store countries from API in the database
                countriesRepository.storeAllCountriesDDBB(countriesDomain)
                apiResponse.data.map { it.toDomain() }
            }

            is NetworkResult.ApiError -> countriesRepository.getAllCountriesDDBB()

            is NetworkResult.ApiException -> countriesRepository.getAllCountriesDDBB()
        }
    }
}