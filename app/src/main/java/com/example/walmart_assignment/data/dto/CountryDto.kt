package com.example.walmart_assignment.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Data Transfer Object (DTO) representing a country.
 *
 * This class is used to parse JSON responses from an API into a Kotlin object.
 * It maps the country-related data fields using Gson serialization annotations.
 */
data class CountryDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("region")
    val region: String,


    @SerializedName("code")
    val code: String,

    @SerializedName("capital")
    val capital: String
)
