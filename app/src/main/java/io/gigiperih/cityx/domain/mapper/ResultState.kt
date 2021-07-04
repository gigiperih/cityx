package io.gigiperih.cityx.domain.mapper

/**
 * wrapper class to hold result
 * OnLoading when first function executed
 * OnSuccess if result is returning value
 * OnError if result not found
 */
sealed class ResultState<out T> {
    class OnLoading<out T> : ResultState<T>()
    data class OnSuccess<out T>(val data: T?, val message: String?) : ResultState<T>()

    // TODO: update mechanism if result is from invalid query or invalid data
    class OnError<out T>(val message: String?) : ResultState<T>()
}