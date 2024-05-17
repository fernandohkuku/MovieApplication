package com.fernando.ku.movieapp.domain.entities

data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String? = null,
    val id: Int,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    val backdrop: String
        get() = "https://image.tmdb.org/t/p/w500$backdrop_path"

    val poster: String
        get() = "https://image.tmdb.org/t/p/w500$poster_path"
}