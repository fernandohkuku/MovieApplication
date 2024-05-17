package com.fernando.ku.movieapp.data.repositories

import androidx.paging.PagingData
import com.fernando.ku.movieapp.data.local.models.MovieModel
import com.fernando.ku.movieapp.data.mappers.toEntity
import com.fernando.ku.movieapp.data.models.base.MovieDto
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getMovieById(id: Int): MovieEntity {
        return remoteDataSource.getMovieById(id).toEntity()
    }

    override fun getRemoteMovies(): Flow<PagingData<MovieDto>> =
        remoteDataSource.getMovies()

    override suspend fun getMovies(pageDetails: MovieRepository.PageDetails): List<MovieEntity> {
        return remoteDataSource.getRemoteMovies(pageDetails).map { it.toEntity() }
    }

}

internal interface MovieRemoteDataSource {
    fun getMovies(): Flow<PagingData<MovieDto>>
    suspend fun getMovieById(movieId: Int): MovieDto
    suspend fun getRemoteMovies(pageDetails: MovieRepository.PageDetails): List<MovieDto>
}

internal interface MovieLocalDataSource {
    suspend fun getMovieById(id: Int): MovieModel
}
