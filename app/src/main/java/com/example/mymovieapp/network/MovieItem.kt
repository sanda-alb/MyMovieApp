package com.example.mymovieapp.network

import com.squareup.moshi.Json

data class MovieItem(
    val id: Int,
    val title: String,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Float,
    val overview: String
)




