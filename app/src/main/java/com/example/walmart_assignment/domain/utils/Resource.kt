package com.example.walmart_assignment.domain.utils

/**
 * A generic sealed class representing the state of a resource.
 * Used to encapsulate different states of data loading: Success, Loading, and Error.
 * Helps in managing UI state by providing a consistent structure for handling API responses.
 * @param T The type of data this resource holds.
 */
sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}