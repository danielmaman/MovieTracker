package com.example.movieshowstracker.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.base.LiveDataWrapper
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.domain.GetMovieByIDUseCase
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import kotlinx.coroutines.*
import org.joda.time.Years
import org.koin.core.KoinComponent

class MoviesViewModel(
    private val getMovieByIDUseCase: GetMovieByIDUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel(), KoinComponent {

    var movieResponse = MutableLiveData<LiveDataWrapper<Movie>>()
    var movieListResponse = MutableLiveData<LiveDataWrapper<MovieList>>()
    private val job = SupervisorJob()
    val mUiScope = CoroutineScope(mainDispatcher + job)
    val mIoScope = CoroutineScope(ioDispatcher + job)

    fun getMovieById(id: String) {
        if(movieResponse.value == null){
            mUiScope.launch {
                movieResponse.value = LiveDataWrapper.loading()
//                setLoadingVisibility(true)
                try {
                    val data = mIoScope.async {
                        return@async getMovieByIDUseCase.getMoviesById(id)
                    }.await()
                    try {
                        movieResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
//                    setLoadingVisibility(false)
                } catch (e: Exception) {
//                    setLoadingVisibility(false)
                    movieResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

    fun loadMovieList() {
        if(movieListResponse.value == null){
            mUiScope.launch {
                movieListResponse.value = LiveDataWrapper.loading()
//                setLoadingVisibility(true)
                try {
                    val data = mIoScope.async {
                        return@async searchMoviesUseCase.getMoviesBySearch("shutter", CinematicType.MOVIE)
                    }.await()
                    try {
                        movieListResponse.value = LiveDataWrapper.success(data)
                    } catch (e: Exception) {
                    }
//                    setLoadingVisibility(false)
                } catch (e: Exception) {
//                    setLoadingVisibility(false)
                    movieListResponse.value = LiveDataWrapper.error(e)
                }
            }
        }
    }

}