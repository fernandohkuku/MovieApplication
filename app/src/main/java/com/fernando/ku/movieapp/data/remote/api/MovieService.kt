package com.fernando.ku.movieapp.data.remote.api

import com.fernando.ku.movieapp.data.models.base.Envelope
import com.fernando.ku.movieapp.data.models.base.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("discover/movie")
    suspend fun getDiscoverMovies(@Query("page") page: Long?): Envelope<MovieDto>

    @GET("movie/now_playing")
    suspend fun getMovies(@Query("page") page: Int?): Envelope<MovieDto>

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Int): MovieDto
}
