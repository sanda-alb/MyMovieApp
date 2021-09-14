package com.example.mymovieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.network.BASE_POSTER_URL


/**
 * DatabaseMovie represents a movie entity in the database.
 */

@Entity
data class DatabaseMovie constructor(
    @PrimaryKey
    val id: Int?,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    var posterLink: String
)

/**
 * Map DatabaseMovies to domain entities
 */

fun List<DatabaseMovie>.asDomainModel(): List<MovieItem> {
    return map {
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
            posterLink = BASE_POSTER_URL + it.posterPath
        )
    }
}