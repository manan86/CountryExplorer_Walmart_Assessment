package com.example.walmart_assignment.domain.usecase

import app.cash.turbine.test
import com.example.walmart_assignment.MainCoroutineRule
import com.example.walmart_assignment.domain.repository.ICountryRepository
import com.example.walmart_assignment.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [GetCountriesUseCase].
 * This test class ensures that the use case correctly handles successful data retrieval
 * and error scenarios when fetching country data from the repository.
 * Uses MockK for mocking dependencies and Turbine for Flow testing.
 */
@ExperimentalCoroutinesApi
class GetCountriesUseCaseTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository = mockk<ICountryRepository>()
    private lateinit var useCase: GetCountriesUseCase

    @Before
    fun setUp() {
        useCase = GetCountriesUseCase(repository)
    }

    @Test
    fun `GIVEN successful repository response WHEN invoke is called THEN return country list`() = runBlocking {
        val mockData = listOf(
            com.example.walmart_assignment.domain.data.CountryDomain("USA", "North America", "US", "Washington D.C."),
            com.example.walmart_assignment.domain.data.CountryDomain("India", "Asia", "IN", "New Delhi")
        )

        coEvery { repository.getCountries() } returns flow { emit(Resource.Success(mockData)) }

        useCase().test {
            val result = awaitItem()
            assertTrue(result is Resource.Success)
            assertEquals(mockData, (result as Resource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN repository error WHEN invoke is called THEN return error`() = runBlocking {

        val errorMessage = "Error Fetching Countries"
        coEvery { repository.getCountries() } returns flow { emit(Resource.Error(errorMessage)) }

        useCase().test {
            val result = awaitItem()
            assertTrue(result is Resource.Error)
            assertEquals(errorMessage, (result as Resource.Error).message)
            awaitComplete()
        }
    }
}