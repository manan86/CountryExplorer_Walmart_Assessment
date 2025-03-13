package com.example.walmart_assignment.data.repository

import com.example.walmart_assignment.data.mapper.CountryMapper
import com.example.walmart_assignment.data.remote.ApiService
import com.example.walmart_assignment.domain.data.CountryDomain
import com.example.walmart_assignment.domain.repository.ICountryRepository
import com.example.walmart_assignment.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of [ICountryRepository] responsible for fetching and mapping country data.
 * This repository interacts with the [ApiService] to retrieve country data and maps
 * the response using [CountryMapper] before exposing it as a flow of [Resource] objects.
 * It ensures proper error handling and emits loading, success, or error states.
 */

class CountryRepositoryImpl(
    private val apiService: ApiService,
    private val mapper: CountryMapper
) : ICountryRepository {

    override fun getCountries(): Flow<Resource<List<CountryDomain>>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getCountries()
            val countries = response.map { mapper.map(it) }
            emit(Resource.Success(countries))

        } catch (e: Exception) {
            emit(Resource.Error("Failed to load countries: ${e.message}"))
        }
    }
}
