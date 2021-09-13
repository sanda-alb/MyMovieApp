package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.asDomainModel
import kotlinx.coroutines.launch


const val API_KEY = "bb340add54f4429cc9cb320eeb25ba8c"

enum class MovieApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MovieApiStatus>()
    val status: LiveData<MovieApiStatus>
        get() = _status

    private val _movieList = MutableLiveData<List<MovieItem>>()
    val movieList: LiveData<List<MovieItem>>
        get() = _movieList

    private val _navigateToSelectedMovie= MutableLiveData<MovieItem>()
    val navigateToSelectedMovie: LiveData<MovieItem>
        get() = _navigateToSelectedMovie


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _status.value = MovieApiStatus.LOADING
            try {
                val movieList = MovieApi.retrofitService.getPopularMovies(API_KEY)
                _movieList.postValue(movieList.asDomainModel())
                _status.value = MovieApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MovieApiStatus.ERROR
                _movieList.value = ArrayList()
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