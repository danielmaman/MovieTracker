package com.example.movieshowstracker.presentation.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.movieshowstracker.R
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.presentation.movies.MoviesRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), MoviesRecyclerViewAdapter.Callbacks {

    private val favoritesViewModel: FavoritesViewModel by viewModel()

    //TODO bindings
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchFavoriteMovieList()
    }

    private fun fetchFavoriteMovieList() {//TODO remove hardcoded default parameter with properly working api method
        favoritesViewModel.getFavoriteMovieList()
            .observe(viewLifecycleOwner, Observer {
                loadRecyclerView(it)
            })
    }

    private fun loadRecyclerView(movieList: List<Movie>) {
        (favoritesRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        favoritesRecyclerView.adapter = MoviesRecyclerViewAdapter(requireContext(), movieList.toMutableList(), this, true)
        favoritesRecyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            1,
            GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun favoriteButtonClicked(isChecked: Boolean, movie: Movie) {
//        favoritesViewModel.favoriteButtonClicked(isChecked, movie).observe(viewLifecycleOwner, Observer {})
    }

    override fun movieExpanded(movie: Movie) {

    }
}