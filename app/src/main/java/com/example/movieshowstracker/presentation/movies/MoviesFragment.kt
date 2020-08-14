package com.example.movieshowstracker.presentation.movies

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.movieshowstracker.R
import com.example.movieshowstracker.base.BaseFragment
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : BaseFragment(), MoviesRecyclerViewAdapter.Callbacks {

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
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fetchMovieList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_bar_menu, menu)
        setSearchView(menu)
    }

    private fun setSearchView(menu: Menu) {
        val searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun fetchMovieList(searchString: String = "batman") {//TODO remove hardcoded default parameter with properly working api method
        moviesViewModel.fetchMovieList(searchString, CinematicType.MOVIE)
            .observe(viewLifecycleOwner, Observer {
                loadRecyclerView(it)
            })
    }

    private fun loadRecyclerView(movieList: List<Movie>) {
        (moviesRecyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        moviesRecyclerView.adapter = MoviesRecyclerViewAdapter(requireContext(), movieList.toMutableList(), this)
        moviesRecyclerView.layoutManager = GridLayoutManager(
            requireContext(),
            1,//TODO remove hardcode
            GridLayoutManager.VERTICAL,
            false
        )
    }

    override fun movieExpanded(movie: Movie) {
        moviesViewModel.movieFieldExpanded(movie).observe(viewLifecycleOwner, Observer {
            (moviesRecyclerView.adapter as MoviesRecyclerViewAdapter).updateListItem(it)
        })
    }

    override fun favoriteButtonClicked(isChecked: Boolean, movie: Movie) {
        moviesViewModel.favoriteButtonClicked(isChecked, movie).observe(viewLifecycleOwner, Observer {})
    }
}
