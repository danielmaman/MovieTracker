package com.example.movieshowstracker.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieshowstracker.R
import com.example.movieshowstracker.base.BaseFragment
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : BaseFragment() {

    private val moviesViewModel: MoviesViewModel by viewModel()

    //TODO databinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchMovieList()
        testButton.setOnClickListener {
            fetchMovieList()
        }
    }

    private fun fetchMovieList() {
        moviesViewModel.fetchMovieList("batman", CinematicType.MOVIE)
            .observe(viewLifecycleOwner, Observer {
                loadRecyclerView(it)
            })
    }

    private fun loadRecyclerView(movieList: List<Movie>) {
        moviesRecyclerView.adapter = MoviesRecyclerViewAdapter(requireContext(), movieList.toMutableList())
        moviesRecyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            2,//TODO remove hardcode
            GridLayoutManager.VERTICAL,
            false
        )
    }
}
