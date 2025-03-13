package com.example.walmart_assignment.presentation.viewmodel


import app.cash.turbine.test
import com.example.walmart_assignment.domain.data.CountryDomain
import com.example.walmart_assignment.domain.usecase.GetCountriesUseCase
import com.example.walmart_assignment.domain.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Unit test class for [CountryViewModel].
 * This class ensures that the ViewModel behaves correctly when handling country data,
 * particularly in scenarios related to search queries and data fetching.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

    private lateinit var viewModel: CountryViewModel
    private lateinit var getCountriesUseCase: GetCountriesUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCountriesUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `empty search query should return full country list`() = runTest {
        // Given
        val mockCountries = listOf(
            CountryDomain(name = "USA", region = "Americas", code = "US", capital = "Washington, D.C."),
            CountryDomain(name = "India", region = "Asia", code = "IN", capital = "New Delhi")
        )

        coEvery { getCountriesUseCase() } returns flowOf(Resource.Success(mockCountries))

        // When
        viewModel = CountryViewModel(getCountriesUseCase)

        viewModel.setSearchQuery("")

        // Then
        viewModel.filteredCountries.test {
            assertEquals(mockCountries, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}

