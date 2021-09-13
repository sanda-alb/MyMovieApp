package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.NetworkMovieContainer
import com.example.mymovieapp.network.NetworkResults
import com.example.mymovieapp.network.asDomainModel
import kotlinx.coroutines.launch


const val API_KEY = "bb340add54f4429cc9cb320eeb25ba8c"


class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    private val _movieList = MutableLiveData<List<MovieItem>>()

    val movieList: LiveData<List<MovieItem>>
        get() = _movieList


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            try {
                val movieList = MovieApi.retrofitService.getPopularMovies(API_KEY)
                _movieList.postValue(movieList.asDomainModel())
                _response.value = "Success: Movie items retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}