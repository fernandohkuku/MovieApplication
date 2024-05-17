package com.fernando.ku.movieapp.domain.usecases.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<T, in Input>(private val background: CoroutineDispatcher) {
    /**
     * Executes the use case logic.
     *
     * @param input The input parameter for the use case operation.
     * @return The result of the use case operation.
     */
    protected abstract suspend fun run(input: Input? = null): T

    /**
     * Invokes the use case, executing its logic and returning a response.
     *
     * @param input The input parameter for the use case operation.
     * @return A [Response] object representing the result of the use case execution.
     */
    suspend operator fun invoke(input: Input? = null): Response<T> = withContext(background) {
        try {
            Response.Success(run(input))
        } catch (error: Exception) {
            Response.Failure(error)
        }
    }
}