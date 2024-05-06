package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import javax.inject.Inject

class GetCountryByOfficialNameUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {

    suspend operator fun invoke(officialName: String): CountryDomain {
        return countriesRepository.getCountryByOfficialName(officialName)
    }
}