package com.wimobile.wmcountriesapp.domain.useCases

import com.wimobile.wmcountriesapp.data.database.entities.CountryEntity
import com.wimobile.wmcountriesapp.data.model.CountryModel
import com.wimobile.wmcountriesapp.data.model.NetworkResult
import com.wimobile.wmcountriesapp.data.repository.CountriesRepository
import com.wimobile.wmcountriesapp.domain.model.CountryDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllCountriesUseCaseTest {

    @RelaxedMockK
    private lateinit var countriesRepository: CountriesRepository

    private lateinit var getAllCountriesUseCase: GetAllCountriesUseCase

    @RelaxedMockK
    private lateinit var countriesResponseDomain: List<CountryDomain>

    @RelaxedMockK
    private lateinit var countriesResponseEntity: List<CountryEntity>

    @RelaxedMockK
    private lateinit var countriesResponseModel: List<CountryModel>

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllCountriesUseCase = GetAllCountriesUseCase(countriesRepository)
        countriesResponseDomain = listOf(CountryDomain())
        countriesResponseEntity = listOf(CountryEntity())
        countriesResponseModel = listOf(CountryModel())
    }

    @Test
    fun `when there's countries information on the db and returns it`() =
        runBlocking {
            //Given
            coEvery {
                countriesRepository.getAllCountriesDDBB()
            } returns countriesResponseEntity

            //When
            getAllCountriesUseCase()

            //Then
            coVerify(exactly = 1) { countriesRepository.getAllCountriesDDBB() }
            coVerify(exactly = 0) { countriesRepository.getAllCountriesAPI() }
            coVerify(exactly = 0) {
                countriesRepository.storeAllCountriesDDBB(
                    countriesResponseDomain
                )
            }
        }

    @Test
    fun `when there's no countries info in db and try to get info from API and it's successful and then store result on db`() {
        runBlocking {
            //Given
            coEvery {
                countriesRepository.getAllCountriesDDBB()
            } returns emptyList()

            coEvery {
                countriesRepository.getAllCountriesAPI()
            } returns NetworkResult.ApiSuccess(countriesResponseModel)


            //When
            getAllCountriesUseCase()

            //Then
            coVerify(exactly = 1) { countriesRepository.getAllCountriesDDBB() }
            coVerify(exactly = 1) { countriesRepository.getAllCountriesAPI() }
            coVerify(exactly = 1) { countriesRepository.storeAllCountriesDDBB(countriesResponseDomain) }

        }
    }

    @Test
    fun `when there's no countries info on db and api call is not successful then return an empty list`() {
        runBlocking {
            //Given
            coEvery {
                countriesRepository.getAllCountriesDDBB()
            } returns emptyList()

            coEvery {
                countriesRepository.getAllCountriesAPI()
            } returns NetworkResult.ApiError(code = 500, message = "Error")

            //When
            getAllCountriesUseCase()

            //Then
            coVerify(exactly = 1) { countriesRepository.getAllCountriesDDBB() }
            coVerify(exactly = 1) { countriesRepository.getAllCountriesAPI() }
            coVerify(exactly = 0) { countriesRepository.storeAllCountriesDDBB(countriesResponseDomain) }
        }
    }

}