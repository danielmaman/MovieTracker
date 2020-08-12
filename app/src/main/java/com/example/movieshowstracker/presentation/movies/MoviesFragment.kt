package com.example.movieshowstracker.presentation.movies

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_bar_menu, menu)
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    fetchMovieList(newText)
                }
                return true
            }
        })
    }

    private fun fetchMovieList(searchString: String = "batman") {//TODO remove hardcode
        moviesViewModel.fetchMovieList(searchString, CinematicType.MOVIE)
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
