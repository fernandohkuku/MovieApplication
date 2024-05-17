package com.fernando.ku.movieapp.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieModel(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String? = null,
    val original_language: String? = "empty",
    val original_title: String? = "empty",
    val overview: String? = "empty",
    val popularity: Double? = 0.0,
    val poster_path: String? = "empty",
    val release_date: String? = null,
    val title: String? = "empty",
    val video: Boolean,
    val vote_average: Double? = 0.0,
    val vote_count: Int? = 0
)