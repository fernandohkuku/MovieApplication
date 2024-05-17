package com.fernando.ku.movieapp.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.fernando.ku.movieapp.data.mappers.toEntity
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import com.fernando.ku.movieapp.domain.usecases.base.UseCaseFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRemoteMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    background: CoroutineDispatcher
) : UseCaseFlow<PagingData<MovieEntity>, Unit>(background) {
    override fun run(input: Unit?): Flow<PagingData<MovieEntity>> {
        return repository.getRemoteMovies().map { it -> it.map { it.toEntity() } }
    }

}