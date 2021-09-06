package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


//enum class MovieDBApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

//    private val _status = MutableLiveData<MovieDBApiStatus >()
//    val status: LiveData<MovieDBApiStatus >
//    get() = _status


    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

        /**
         * Call getMarsRealEstateProperties() on init so we can display status immediately.
         */
        init {
            getMovies()
        }

        /**
         * Sets the value of the status LiveData to the Mars API status.
         */
        private fun getMovies() {
            _response.value = "Set the Movies API Response here!"
        }


}