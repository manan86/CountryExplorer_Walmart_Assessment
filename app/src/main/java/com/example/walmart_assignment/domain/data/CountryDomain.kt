package com.example.walmart_assignment.domain.data

/**
 * Domain model representing a country.
 * This class is used within the application's domain layer to encapsulate
 * country-related data, ensuring separation from data transfer objects (DTOs).
 */
data class CountryDomain(
    val name: String,
    val region: String,
    val code: String,
    val capital: String
)



