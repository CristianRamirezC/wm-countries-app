package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.database.dao.CountriesDao
import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import com.wimobile.wmcountriesapp.domain.model.toDomain
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val countriesRepository: CountriesRepository
) {

    suspend operator fun invoke(name: String): CountryDomain {
        return countriesRepository.getCountryByName(name)
    }
}