package com.example.mymovieapp

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
