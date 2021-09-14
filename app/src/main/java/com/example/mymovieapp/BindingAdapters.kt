package com.example.mymovieapp

import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mymovieapp.domain.MovieItem
import com.example.mymovieapp.domain.genreMap
import com.example.mymovieapp.overview.MovieApiStatus
import com.example.mymovieapp.overview.PhotoGridAdapter

@BindingAdapter("posterUrl")
fun bindImage(imgView: ImageView, posterPath: String?) {
    posterPath?.let {
        val posterUri = posterPath.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(posterUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_baseline_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<MovieItem>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}


@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView,
               status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MovieApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MovieApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("movieGenre")
fun setGenre(txtView: TextView, genreIds: List<Int>) {
    val genreList = mutableListOf<String>()
    for (genreId in genreIds) {
        val genre = genreMap[genreId]
        genreList.add(genre!!)
    }
    txtView.text = genreList.joinToString()
}