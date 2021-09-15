package com.example.mymovieapp

import android.app.Application
import timber.log.Timber

class MyMovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}