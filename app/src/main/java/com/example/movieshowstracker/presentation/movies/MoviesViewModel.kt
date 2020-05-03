package com.example.movieshowstracker.presentation.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.base.LiveDataWrapper
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.domain.GetMovieByIDUseCase
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

class MoviesViewModel(
    private val getMovieByIDUseCase: GetMovieByIDUseCase,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel(), KoinComponent {

    var movieResponse = MutableLiveData<LiveDataWrapper<Movie>>()
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

}