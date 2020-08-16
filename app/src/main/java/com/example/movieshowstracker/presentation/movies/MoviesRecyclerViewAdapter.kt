package com.example.movieshowstracker.presentation.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieshowstracker.R
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.databinding.LayoutMovieItemBinding

class MoviesRecyclerViewAdapter(
    private val context: Context,
    private var moviesList: MutableList<Movie>,
    private val callbacks: Callbacks,
    private val allExpanded: Boolean = false
) : RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = LayoutMovieItemBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding) //TODO binding
    }

    fun updateListItems(newList: MutableList<Movie>) {
        val diffResult = DiffUtil.calculateDiff(MovieDiffCallback(this.moviesList, newList))
        diffResult.dispatchUpdatesTo(this)
        moviesList.clear()
        moviesList.addAll(newList)
    }

    fun updateListItem(updatedMovie: Movie) {
        val movie = moviesList
            .single { movie -> movie.imdbID == updatedMovie.imdbID }
        val index = moviesList.indexOf(movie)
        val newList = moviesList.toMutableList()
        updatedMovie.isFavorite = movie.isFavorite
        newList[index] = updatedMovie
        updateListItems(newList)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = moviesList[position]
        holder.binding.movie = movie
        Glide.with(context).load(movie.poster).into(holder.movieImageView)
        setExpandableLayout(holder, position)
        setToggleButton(holder.favoriteToggleButton, position)
    }

    private fun setExpandableLayout(holder: MovieViewHolder, position: Int) {
        if (allExpanded) holder.descriptionLayout.visibility = View.VISIBLE
        holder.itemView.setOnClickListener {
            val movie = moviesList[position]
            if (holder.descriptionLayout.visibility == View.VISIBLE) {
                holder.descriptionLayout.visibility = View.GONE
            } else if (holder.descriptionLayout.visibility == View.GONE) {
                callbacks.movieExpanded(movie)
                holder.descriptionLayout.visibility = View.VISIBLE
            }
            holder.setIsRecyclable(false)
        }
    }

    private fun setToggleButton(
        favoriteToggleButton: ToggleButton,
        position: Int
    ) {
        favoriteToggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            val movie = moviesList[position]
            movie.isFavorite = isChecked
            callbacks.favoriteButtonClicked(isChecked, movie)
        }
    }

    class MovieViewHolder(val binding: LayoutMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieImageView: ImageView = binding.movieCoverImageView
        val favoriteToggleButton: ToggleButton = binding.buttonFavorite
        val descriptionLayout: View = binding.descriptionLayout
    }//TODO use bindings and remove findViewById

    interface Callbacks {
        fun favoriteButtonClicked(isChecked: Boolean, movie: Movie)
        fun movieExpanded(movie: Movie)
    }
}