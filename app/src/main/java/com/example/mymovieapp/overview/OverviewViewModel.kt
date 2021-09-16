package com.example.mymovieapp.overview

import android.app.Application
import androidx.lifecycle.*
import com.example.mymovieapp.database.getDatabase
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.network.API_KEY
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.asDomainModel
import com.example.mymovieapp.repository.MoviesRepository
import kotlinx.coroutines.launch


enum class MovieApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _navigateToSelectedMovie= MutableLiveData<MovieItem?>()
    val navigateToSelectedMovie: LiveData<MovieItem?>
        get() = _navigateToSelectedMovie

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val moviesRepository = MoviesRepository(getDatabase(application))

    val movieList = moviesRepository.movies

    init {
        refreshDataFromRepository()
    }


    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                moviesRepository.refreshMovies()
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
            }
        }
    }




    fun displayMovieDetails(movieItem: MovieItem) {
        _navigateToSelectedMovie.value = movieItem
    }

    fun displayMovieDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }
}