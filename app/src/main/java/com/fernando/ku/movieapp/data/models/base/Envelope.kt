package com.fernando.ku.movieapp.data.models.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Envelope<T>(
    @get:Json(name = "page") val page: Int,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int,
    @get:Json(name = "results") val results: List<T>
)
