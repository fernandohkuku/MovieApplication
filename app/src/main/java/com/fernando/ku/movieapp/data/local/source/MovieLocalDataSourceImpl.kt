package com.fernando.ku.movieapp.data.local.source

import com.fernando.ku.movieapp.data.local.dao.MovieDao
import com.fernando.ku.movieapp.data.local.models.MovieModel
import com.fernando.ku.movieapp.data.repositories.MovieLocalDataSource
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
) : MovieLocalDataSource {
    override suspend fun getMovieById(id: Int): MovieModel {
        return dao.getMovieById(id)
    }
}