package com.example.walmart_assignment.data.remote

import com.example.walmart_assignment.data.dto.CountryDto
import com.example.walmart_assignment.data.remote.ApiConstants.ENDPOINT_COUNTRIES
import retrofit2.http.GET

/**
 * Retrofit service interface for making API requests.
 *
 * Defines network calls related to country data retrieval.
 * Uses Retrofit annotations to specify HTTP methods and endpoints.
 */
interface ApiService {
    @GET(ENDPOINT_COUNTRIES)
    suspend fun getCountries(): List<CountryDto>
}


