package com.example.movieshowstracker.presentation.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieshowstracker.R
import com.example.movieshowstracker.data.model.Movie

class MoviesRecyclerViewAdapter(private val context: Context, private var moviesList: MutableList<Movie>, private val callbacks: Callbacks) :
    RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_movie_item, parent, false) //TODO binding
        )
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
        Glide.with(context).load(movie.poster).into(holder.movieImageView)
        setTextViews(holder, movie)
        setExpandableLayout(holder, position)
        setToggleButton(holder.favoriteToggleButton, position)
    }

    private fun setTextViews(
        holder: MovieViewHolder,
        movie: Movie
    ) {
        holder.titleTextView.text = movie.title
        holder.yearTextView.text = movie.year.toString()
        holder.ratingTextView.text = movie.imdbRating.toString()
        holder.descriptionTextView.text = movie.plot
    }

    private fun setExpandableLayout(holder: MovieViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val movie = moviesList[position]
            if (holder.descriptionLayout.visibility == View.VISIBLE) {
                holder.descriptionLayout.visibility = View.GONE
            } else {
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
        favoriteToggleButton.isChecked = moviesList[position].isFavorite
        favoriteToggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            val movie = moviesList[position]
            movie.isFavorite = isChecked
            callbacks.favoriteButtonClicked(isChecked, movie )
        }
    }

    class MovieViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val movieImageView: ImageView = item.findViewById(R.id.movieCoverImageView)
        val titleTextView: TextView = item.findViewById(R.id.titleTextView)
        val ratingTextView: TextView = item.findViewById(R.id.ratingTextView)
        val yearTextView: TextView = item.findViewById(R.id.yearTextView)
        val favoriteToggleButton: ToggleButton = item.findViewById(R.id.button_favorite)
        val descriptionTextView: TextView = item.findViewById(R.id.movieDescriptionTextView)
        val descriptionLayout: View = item.findViewById(R.id.descriptionLayout)
    }//TODO move to binding

    interface Callbacks{
        fun favoriteButtonClicked(isChecked: Boolean, movie: Movie)
        fun movieExpanded(movie: Movie)
    }
}