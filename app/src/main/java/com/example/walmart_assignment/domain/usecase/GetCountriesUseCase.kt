package com.example.walmart_assignment.domain.usecase

import com.example.walmart_assignment.domain.repository.ICountryRepository
import com.example.walmart_assignment.domain.utils.Resource
import com.example.walmart_assignment.domain.data.CountryDomain
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving a list of countries.
 * Acts as an intermediary between the repository and the UI layer,
 * enforcing business logic and ensuring clean architecture principles.
 */
class GetCountriesUseCase(private val repository: ICountryRepository) {
    operator fun invoke(): Flow<Resource<List<CountryDomain>>> {
        return repository.getCountries()
    }
}

