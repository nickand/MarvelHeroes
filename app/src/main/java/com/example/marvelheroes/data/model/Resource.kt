package com.example.marvelheroes.data.model

/**
 * Class that encapsulate both the data and its state.
 */
sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error(
        message: String,
        error: Throwable? = null
    ) : Resource<Nothing>(null, message)
}
