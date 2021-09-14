package com.example.mymovieapp.network

import com.example.mymovieapp.database.DatabaseMovie
import com.example.mymovieapp.domain.MovieItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w500"

@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(val page: Int, val results: List<NetworkResults>)


@JsonClass(generateAdapter = true)
data class NetworkResults(

    @Json(name = "adult")
val adult: Boolean?,

    @Json(name = "backdrop_path")
val backdropPath: String?,

    @Json(name = "genre_ids")
val genreIds: List<Int>?,

    @Json(name = "id")
val id: Int?,

    @Json(name = "original_language")
val originalLanguage: String?,

    @Json(name = "original_title")
val originalTitle: String?,

    @Json(name = "overview")
val overview: String?,

    @Json(name = "popularity")
val popularity: Double?,

    @Json(name = "poster_path")
val posterPath: String?,

    @Json(name = "release_date")
val releaseDate: String?,

    @Json(name = "title")
val title: String?,

    @Json(name = "video")
val video: Boolean?,

    @Json(name = "vote_average")
val voteAverage: Double?,

    @Json(name = "vote_count")
val voteCount: Int?)


fun NetworkMovieContainer.asDomainModel(): List<MovieItem> {
    return results.map {
        MovieItem(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            posterLink =  BASE_POSTER_URL + it.posterPath
        )
    }
}


/**
 * Convert Network results to database objects
 */

fun NetworkMovieContainer.asDataBaseModel() : List<DatabaseMovie> {
    return results.map {
        DatabaseMovie(
            adult = it.adult,
            backdropPath = it.backdropPath,
            genreIds = it.genreIds,
            id = it.id,
            originalLanguage = it.originalLanguage,
            originalTitle = it.originalTitle,
            overview = it.overview,
            popularity = it.popularity,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            video = it.video,
            voteAverage = it.voteAverage,
            voteCount = it.voteCount,
            posterLink =  BASE_POSTER_URL + it.posterPath
        )
    }
}

