package com.example.movieshowstracker.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.domain.DeleteMovieFromDbUseCase
import com.example.movieshowstracker.domain.InsertMovieToDbUseCase
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import org.koin.core.KoinComponent

class MoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val insertMovieToDbUseCase: InsertMovieToDbUseCase,
    private val deleteMovieFromDbUseCase: DeleteMovieFromDbUseCase
) : ViewModel(), KoinComponent {

    fun fetchMovieList(searchString: String, cinematicType: CinematicType) =
        searchMoviesUseCase.execute(SearchMoviesUseCase.Request(searchString, cinematicType))

    fun favoriteButtonClicked(checked: Boolean, movie: Movie) =
        if (checked) insertMovieToDbUseCase.execute(InsertMovieToDbUseCase.Request(movie))
        else deleteMovieFromDbUseCase.execute(DeleteMovieFromDbUseCase.Request(movie)) //TODO save and remove to room

}