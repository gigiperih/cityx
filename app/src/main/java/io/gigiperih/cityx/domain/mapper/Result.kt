package io.gigiperih.cityx.domain.mapper

sealed class Result<out T> {
    class OnLoading<out T> : Result<T>()
    data class OnSuccess<out T>(val data: T?, val message: String?) : Result<T>()
    class OnError<out T>(val message: String?) : Result<T>()
}