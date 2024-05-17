package com.fernando.ku.movieapp.data.models.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
    @get:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdropPath: String? = null,
    @get:Json(name = "id") val id: Int,
    @field:Json(name = "original_language") val originalLanguage: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @get:Json(name = "overview") val overview: String,
    @get:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val posterPath: String,
    @field:Json(name = "release_date") val releaseDate: String? = null,
    @get:Json(name = "title") val title: String,
    @get:Json(name = "video") val video: Boolean,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "vote_count") val voteCount: Int
)
