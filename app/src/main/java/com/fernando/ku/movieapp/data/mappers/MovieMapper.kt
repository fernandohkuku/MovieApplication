package com.fernando.ku.movieapp.data.mappers

import com.fernando.ku.movieapp.data.local.models.MovieModel
import com.fernando.ku.movieapp.data.models.base.MovieDto
import com.fernando.ku.movieapp.domain.entities.MovieEntity


internal fun MovieDto.toModel() = MovieModel(
    id = id,
    adult = adult,
    backdrop_path = backdropPath,
    original_language = originalLanguage,
    original_title = originalTitle,
    overview = overview,
    popularity = popularity,
    poster_path = posterPath,
    release_date = releaseDate,
    title = title,
    video = video,
    vote_average = voteAverage,
    vote_count = voteCount
)

internal fun MovieDto.toEntity() = MovieEntity(
    id = id,
    adult = adult,
    backdrop_path = backdropPath,
    original_language = originalLanguage,
    original_title = originalTitle,
    overview = overview,
    popularity = popularity,
    poster_path = posterPath,
    release_date = releaseDate,
    title = title,
    video = video,
    vote_average = voteAverage,
    vote_count = voteCount
)

internal fun MovieModel.toEntity() = MovieEntity(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity ?: 0.0,
    poster_path = poster_path,
    release_date = release_date,
    title = title,
    video = video,
    vote_average = vote_average ?: 0.0,
    vote_count = vote_count ?: 0


)

internal fun MovieModel.toDto() = MovieDto(
    id = id,
    adult = adult,
    backdropPath = backdrop_path,
    originalLanguage = original_language.toString(),
    originalTitle = original_title.toString(),
    overview = overview.toString(),
    popularity = popularity ?: 0.0,
    posterPath = poster_path.toString(),
    releaseDate = release_date,
    title = title.toString(),
    video = video,
    voteAverage = vote_average ?: 0.0,
    voteCount = vote_count ?: 0
)