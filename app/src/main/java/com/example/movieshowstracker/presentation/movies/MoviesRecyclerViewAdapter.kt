package com.example.movieshowstracker.presentation.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieshowstracker.R
import com.example.movieshowstracker.data.model.Movie
import org.w3c.dom.Text

class MoviesRecyclerViewAdapter(val context: Context, list: MutableList<Movie>): RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder>() {

    var moviesList = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_movie_item,parent,false))
    }

    fun updateListItems(updatedList: MutableList<Movie>){
        moviesList.clear()
        moviesList = updatedList
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie : Movie = moviesList[position]
        Glide.with(context).load(movie.poster).into(holder.movieImageView)
        holder.titleTextView.text = movie.title
        holder.yearTextView.text = movie.year.toString()
        holder.ratingTextView.text = movie.imdbRating.toString()
    }

    class MovieViewHolder(item: View): RecyclerView.ViewHolder(item){
        val movieImageView : ImageView = item.findViewById(R.id.movieCoverImageView)
        val titleTextView : TextView = item.findViewById(R.id.titleTextView)
        val ratingTextView : TextView = item.findViewById(R.id.ratingTextView)
        val yearTextView : TextView = item.findViewById(R.id.yearTextView)
    }//TODO move to binding
}