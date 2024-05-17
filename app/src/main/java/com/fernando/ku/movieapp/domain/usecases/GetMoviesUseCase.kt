package com.fernando.ku.movieapp.domain.usecases

import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import com.fernando.ku.movieapp.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    background: CoroutineDispatcher
) : UseCase<List<MovieEntity>, MovieRepository.PageDetails>(background) {
    override suspend fun run(input: MovieRepository.PageDetails?): List<MovieEntity> {
        requireNotNull(input) { "page details can't be null" }
        return repository.getMovies(input)
    }

}