package com.example.mymovieapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.databinding.GridViewItemBinding
import com.example.mymovieapp.domain.MovieItem

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<MovieItem,
        PhotoGridAdapter.MovieItemViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MovieItemViewHolder {
        return MovieItemViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MovieItemViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onClick(movieItem)
        }
        holder.bind(movieItem)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class MovieItemViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItem) {
            binding.movieItem = movieItem
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (movieItem: MovieItem) -> Unit) {
        fun onClick(movieItem: MovieItem) = clickListener(movieItem)
    }

}