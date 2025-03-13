package com.example.walmart_assignment.data.remote

import com.example.walmart_assignment.data.mapper.CountryMapper
import com.example.walmart_assignment.data.mapper.CountryMapperImpl
import com.example.walmart_assignment.data.remote.ApiConstants.BASE_URL
import com.example.walmart_assignment.data.repository.CountryRepositoryImpl
import com.example.walmart_assignment.domain.repository.ICountryRepository
import com.example.walmart_assignment.domain.usecase.GetCountriesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Object responsible for providing dependencies in the application.
 * This acts as a simple service locator, initializing and managing instances
 * of API services, mappers, repositories, and use cases. It ensures that
 * dependencies are created lazily and shared where needed.
 */
object DependencyProvider {

    private val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private val countryMapper: CountryMapper by lazy {
        CountryMapperImpl()
    }

    private val countryRepository: ICountryRepository by lazy {
        CountryRepositoryImpl(apiService, countryMapper)
    }

    val getCountriesUseCase: GetCountriesUseCase by lazy {
        GetCountriesUseCase(countryRepository)
    }
}
