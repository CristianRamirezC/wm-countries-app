package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import javax.inject.Inject

class GetCountryByFifaUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {

    suspend operator fun invoke(fifa: String): CountryDomain? {
        return  countriesRepository.getCountryByFifa(fifa)
    }

}