package com.example.mymovieapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.mymovieapp.database.MoviesDatabase
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.network.API_KEY
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.asDataBaseModel
import com.example.mymovieapp.network.asDomainModel
import com.example.mymovieapp.overview.MovieApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MoviesRepository(private val database: MoviesDatabase) {

    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val movieList = MovieApi.retrofitService.getPopularMovies(API_KEY)
//            database.movieDao.insertAll(movieList.asDataBaseModel())
        }
    }

    /**
     * LiveData object to read the video playlist from the database. This LiveData object is
     * automatically updated when the database is updated.
     */

    val movies: LiveData<List<MovieItem>> = Transformations.map(database.movieDao.getMovies()) {
        it.asDomainModel()
    }
}

