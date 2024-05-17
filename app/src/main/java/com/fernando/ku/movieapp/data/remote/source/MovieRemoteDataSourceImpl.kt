package com.fernando.ku.movieapp.data.remote.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fernando.ku.movieapp.data.local.AppDatabase
import com.fernando.ku.movieapp.data.models.base.MovieDto
import com.fernando.ku.movieapp.data.remote.api.MovieService
import com.fernando.ku.movieapp.data.remote.paging.MovieMediator
import com.fernando.ku.movieapp.data.repositories.MovieRemoteDataSource
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import com.fernando.ku.movieapp.domain.repositories.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService,
    private val db: AppDatabase
) : MovieRemoteDataSource {

    private var hasMoreData = true
    private var nextPageNumber: Long = 1
    private val movies: MutableList<MovieDto> = mutableListOf()

    override fun getMovies(): Flow<PagingData<MovieDto>> =
        Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = true),
            pagingSourceFactory = { MovieMediator(service, db) }
        ).flow

    override suspend fun getMovieById(movieId: Int): MovieDto {
        return service.getMovieById(movieId)
    }

    override suspend fun getRemoteMovies(pageDetails: MovieRepository.PageDetails): List<MovieDto> {
        updateNextPageDetails(pageDetails) {
            movies.clear()
        }

        if (!hasMoreData) {
            return listOf()
        }

        val results = service.getDiscoverMovies(nextPageNumber * pageDetails.size).results

        movies.addAll(results)

        return movies
    }


    private fun updateNextPageDetails(
        pageDetails: MovieRepository.PageDetails,
        isFirstPage: () -> Unit
    ) {
        if (pageDetails.isFirstPage) {
            isFirstPage()
            nextPageNumber = 1
            hasMoreData = true
        } else {
            ++nextPageNumber
        }
    }
}