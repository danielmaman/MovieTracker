package com.example.movieshowstracker.presentation.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.movieshowstracker.R
import com.example.movieshowstracker.base.LiveDataWrapper
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    val moviesViewModel : MoviesViewModel by viewModel()
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
        moviesViewModel.loadMovieList()
        moviesViewModel.movieListResponse.observe(viewLifecycleOwner, mDataObserver)
    }


    private val mDataObserver = Observer<LiveDataWrapper<MovieList>> { result ->
        when (result?.responseRESPONSESTATUS) {
            LiveDataWrapper.RESPONSESTATUS.LOADING -> {
                // Loading data
            }
            LiveDataWrapper.RESPONSESTATUS.ERROR -> {
//                // Error for http request
//                logD(TAG,"LiveDataResult.Status.ERROR = ${result.response}")
//                error_holder.visibility = View.VISIBLE
//                /showToast("Error in getting data")
//                testTextView.text = "Error in getting data"

            }
            LiveDataWrapper.RESPONSESTATUS.SUCCESS -> {
                moviesRecyclerView.adapter = MoviesRecyclerViewAdapter(requireContext(), result.response?.movieList?.toMutableList() ?: mutableListOf())
                moviesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
//                testTextView.text = result.response?.title
//                // Data from API
//                logD(TAG,"LiveDataResult.Status.SUCCESS = ${result.response}")
//                val mainItemReceived = result.response as AllPeople
//                val  listItems =  mainItemReceived.results as ArrayList<AllPeopleResult>
//                processData(listItems)
            }
        }
    }
}
