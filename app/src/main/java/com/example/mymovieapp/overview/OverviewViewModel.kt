package com.example.mymovieapp.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.network.MovieApi
import com.example.mymovieapp.network.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//enum class MovieDBApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

//    private val _status = MutableLiveData<MovieDBApiStatus >()
//    val status: LiveData<MovieDBApiStatus >
//    get() = _status


    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response


    init {
        getMovies()
    }

    private fun getMovies() {
        MovieApi.retrofitService.getPopularMovies("bb340add54f4429cc9cb320eeb25ba8c", 1).enqueue(
            object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            })
    }


}