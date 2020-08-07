package com.example.movieshowstracker.presentation.movies

import androidx.recyclerview.widget.DiffUtil
import com.example.movieshowstracker.data.model.Movie


class MovieDiffCallback(
    private var newMovies: List<Movie>,
    private var oldMovies: List<Movie>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldMovies.size
    }

    override fun getNewListSize(): Int {
        return newMovies.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldMovies[oldItemPosition].imdbID == newMovies[newItemPosition].imdbID
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldMovies[oldItemPosition].equals(newMovies[newItemPosition])
    }
}