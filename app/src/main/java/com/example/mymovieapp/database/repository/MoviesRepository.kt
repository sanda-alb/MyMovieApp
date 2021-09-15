package com.example.mymovieapp.database.repository

import com.example.mymovieapp.database.MoviesDatabase
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.asDataBaseModel
import com.example.mymovieapp.overview.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MoviesRepository(private val database: MoviesDatabase) {

    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh movies is called");
            val movieList = MovieApi.retrofitService.getPopularMovies(API_KEY)
            database.movieDao.insertAll(movieList.asDataBaseModel())
        }
    }
}
