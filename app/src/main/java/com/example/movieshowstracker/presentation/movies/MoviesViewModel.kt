package com.example.movieshowstracker.presentation.movies

import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import org.koin.core.KoinComponent

class MoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel(), KoinComponent {

    fun fetchMovieList(searchString: String, cinematicType: CinematicType) =
        searchMoviesUseCase.execute(SearchMoviesUseCase.Request(searchString, cinematicType))

}