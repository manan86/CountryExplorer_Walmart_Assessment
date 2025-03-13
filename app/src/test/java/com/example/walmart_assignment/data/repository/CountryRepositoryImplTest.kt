package com.example.walmart_assignment.data.repository

import app.cash.turbine.test
import com.example.walmart_assignment.MainCoroutineRule
import com.example.walmart_assignment.data.mapper.CountryMapperImpl
import com.example.walmart_assignment.data.remote.ApiService
import com.example.walmart_assignment.domain.data.CountryDomain
import com.example.walmart_assignment.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [CountryRepositoryImpl].
 * This test class verifies the repository's behavior when fetching country data,
 * ensuring correct handling of successful API responses and error scenarios.
 * Uses MockK for mocking dependencies and Turbine for testing Kotlin Flows.
 */
@ExperimentalCoroutinesApi
class CountryRepositoryImplTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val apiService = mockk<ApiService>()
    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setUp() {
        repository = CountryRepositoryImpl(apiService, CountryMapperImpl())
    }

    @Test
    fun `GIVEN successful API response WHEN getCountries is called THEN return country list`() = runBlocking {
        val mockResponse = listOf(
            com.example.walmart_assignment.data.dto.CountryDto("USA", "North America", "US", "Washington D.C."),
            com.example.walmart_assignment.data.dto.CountryDto("India", "Asia", "IN", "New Delhi")
        )
        val expectedData = listOf(
            CountryDomain("USA", "North America", "US", "Washington D.C."),
            CountryDomain("India", "Asia", "IN", "New Delhi")
        )

        coEvery { apiService.getCountries() } returns mockResponse

        val flow = repository.getCountries()

        flow.test {
            assertTrue(awaitItem() is Resource.Loading)

            val success = awaitItem() as Resource.Success
            assertEquals(expectedData, success.data)

            awaitComplete()
        }
    }

    @Test
    fun `GIVEN API failure WHEN getCountries is called THEN return error`() = runBlocking {

        coEvery { apiService.getCountries() } throws RuntimeException("Network Error")

        val flow = repository.getCountries()

        flow.test {
            assertTrue(awaitItem() is Resource.Loading)

            val error = awaitItem() as Resource.Error
            assertEquals("Failed to load countries: Network Error", error.message)

            awaitComplete()
        }
    }
}