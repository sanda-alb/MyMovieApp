package com.example.mymovieapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovieapp.domain.MovieItem


class DetailViewModelFactory(
    val movieItem: MovieItem,
    val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(movieItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}