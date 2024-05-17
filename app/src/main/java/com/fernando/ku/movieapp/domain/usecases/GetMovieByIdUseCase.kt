package com.fernando.ku.movieapp.domain.usecases

import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import com.fernando.ku.movieapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val repository: MovieRepository,
    background: CoroutineDispatcher
) : UseCase<MovieEntity, Int>(background) {
    override suspend fun run(input: Int?): MovieEntity {
        requireNotNull(input) { "Id can't be null" }
        return repository.getMovieById(input)
    }

}