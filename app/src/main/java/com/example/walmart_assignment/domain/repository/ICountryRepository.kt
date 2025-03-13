package com.example.walmart_assignment.domain.repository

import com.example.walmart_assignment.domain.utils.Resource
import com.example.walmart_assignment.domain.data.CountryDomain
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for fetching country data.
 * Defines a contract for retrieving a list of countries as a flow of [Resource] objects,
 * ensuring proper handling of loading, success, and error states.
 */
interface ICountryRepository {
    fun getCountries(): Flow<Resource<List<CountryDomain>>>
}

