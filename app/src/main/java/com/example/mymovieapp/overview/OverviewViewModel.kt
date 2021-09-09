package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.NetworkMovieContainer
import com.example.mymovieapp.network.NetworkResults
import kotlinx.coroutines.launch


const val API_KEY = "bb340add54f4429cc9cb320eeb25ba8c"


class OverviewViewModel : ViewModel() {

    val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    val _item = MutableLiveData<NetworkResults>()

    val item: LiveData<NetworkResults>
        get() = _item


    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            try {
                val listResult = MovieApi.retrofitService.getPopularMovies(API_KEY)
                _response.value = "Success: ${listResult.results.size} Movie items retrieved"
                if (listResult.results.size >0 ) {
                    val result = listResult.results[0]
                    result.link = "https://image.tmdb.org/t/p/w500" + result.posterPath
                    _item.value = result
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}