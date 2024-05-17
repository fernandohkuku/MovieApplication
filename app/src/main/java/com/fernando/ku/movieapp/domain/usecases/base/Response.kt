package com.fernando.ku.movieapp.domain.usecases.base

sealed class Response<out T> {

    /**
     * Data class representing a successful response.
     *
     * @property response The data carried by the success response.
     */
    data class Success<out T>(val response: T) : Response<T>()

    /**
     * Data class representing a failure response.
     *
     * @property error The exception indicating the failure.
     */
    data class Failure(val error: Exception) : Response<Nothing>()

    /**
     * Checks if the response is a success.
     */
    val isSuccess get() = this is Success<T>

    /**
     * Checks if the response is an error.
     */
    val isError get() = this is Failure

    /**
     * Performs an action based on whether the response is a success or a failure.
     *
     * @param onSuccess The action to perform if the response is a success.
     * @param onFailure The action to perform if the response is a failure.
     * @return The result of the action.
     */
    fun fold(onSuccess: (T) -> Unit = {}, onFailure: (Exception) -> Any = {}): Any =
        when (this) {
            is Success -> onSuccess(response)
            is Failure -> onFailure(error)
        }

    /**
     * Subscribes to the response, performing suspend functions based on whether it's a success or a failure.
     *
     * @param onSuccess The suspend function to execute if the response is a success.
     * @param onFailure The suspend function to execute if the response is a failure.
     * @return The result of the suspend function execution.
     */
    suspend fun subscribe(
        onSuccess: suspend (T) -> Any = {},
        onFailure: suspend (Exception) -> Any = {}
    ): Any =
        when (this) {
            is Success -> onSuccess(response)
            is Failure -> onFailure(error)
        }
}
