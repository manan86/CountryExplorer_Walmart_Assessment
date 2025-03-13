package com.example.walmart_assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmart_assignment.domain.data.CountryDomain
import com.example.walmart_assignment.domain.usecase.GetCountriesUseCase
import com.example.walmart_assignment.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel for managing country data and search functionality.
 * Handles fetching country data using [GetCountriesUseCase], maintains UI state,
 * and filters results based on the user's search query. Uses Kotlin Flows for
 * reactive data handling and ensures optimized updates with debounce and distinctUntilChanged.
 */
class CountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val _countries = MutableStateFlow<Resource<List<CountryDomain>>>(Resource.Loading)
    val countries: StateFlow<Resource<List<CountryDomain>>> = _countries

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _filteredCountries = MutableStateFlow<List<CountryDomain>>(emptyList())
    val filteredCountries: StateFlow<List<CountryDomain>> = _filteredCountries

    private var originalList: List<CountryDomain> = emptyList()

    init {
        fetchCountries()
        observeSearchQuery()
    }

    fun fetchCountries() {
        viewModelScope.launch (Dispatchers.IO){
            getCountriesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _countries.value = Resource.Loading
                    is Resource.Success -> {
                        result.data?.let { list ->
                            originalList = list
                            _filteredCountries.value = list
                            _countries.value = Resource.Success(list)
                        }
                    }
                    is Resource.Error -> _countries.value = Resource.Error(result.message ?: "Unknown error")
                }

            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query.trim()
    }

    private fun observeSearchQuery() {
        viewModelScope.launch (Dispatchers.IO){
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    _filteredCountries.value = if (query.isEmpty()) {
                        originalList
                    } else {
                        originalList.filter {
                            it.name.contains(query, ignoreCase = true) ||
                                    it.capital.contains(query, ignoreCase = true)
                        }
                    }
                }
        }
    }
}
