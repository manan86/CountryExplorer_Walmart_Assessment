package com.example.walmart_assignment.data.mapper

import com.example.walmart_assignment.data.dto.CountryDto
import com.example.walmart_assignment.domain.data.CountryDomain

/**
 * Interface for mapping a [CountryDto] to a [CountryDomain].
 *
 * This mapper is responsible for converting data transfer objects (DTOs)
 * received from an API into domain-level models used within the application.
 */
interface CountryMapper {
    fun map(dto: CountryDto): CountryDomain
}


