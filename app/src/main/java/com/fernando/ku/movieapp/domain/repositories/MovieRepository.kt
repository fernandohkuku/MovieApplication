package com.fernando.ku.movieapp.domain.repositories

import androidx.paging.PagingData
import com.fernando.ku.movieapp.data.models.base.MovieDto
import com.fernando.ku.movieapp.domain.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    data class PageDetails(
        val isFirstPage: Boolean,
        val size: Long
    )

    suspend fun getMovieById(id: Int): MovieEntity

    fun getRemoteMovies(): Flow<PagingData<MovieDto>>

    suspend fun getMovies(pageDetails: PageDetails): List<MovieEntity>
}