package com.example.walmart_assignment.data.mapper

import com.example.walmart_assignment.data.dto.CountryDto
import com.example.walmart_assignment.domain.data.CountryDomain

/**
 * Implementation of [CountryMapper] that maps a [CountryDto] to a [CountryDomain].
 *
 * This class provides the logic to transform data from the DTO layer (network or database)
 * into the domain layer, ensuring separation of concerns within the application architecture.
 */
class CountryMapperImpl : CountryMapper {
    override fun map(dto: CountryDto): CountryDomain {
        return CountryDomain(
            name = dto.name,
            region = dto.region,
            code = dto.code,
            capital = dto.capital
        )
    }
}


