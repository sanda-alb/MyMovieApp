package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.NetworkResults
import kotlinx.coroutines.launch




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
                val listResult = MovieApi.retrofitService.getPopularMovies("bb340add54f4429cc9cb320eeb25ba8c")
                _response.value = "Success: ${listResult.results.size} Movie items retrieved"
                if (listResult.results.size >0 ) {
                    _item.value = listResult.results[0]
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}