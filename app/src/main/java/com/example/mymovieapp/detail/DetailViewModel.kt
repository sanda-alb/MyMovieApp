package com.example.mymovieapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mymovieapp.domain.MovieItem


class DetailViewModel( movieItem: MovieItem,
                       app: Application) : AndroidViewModel(app) {
    private val _selectedMovie = MutableLiveData<MovieItem>()
    val selectedMovie: LiveData<MovieItem>
        get() = _selectedMovie

    init{
        _selectedMovie.value = movieItem
    }
}