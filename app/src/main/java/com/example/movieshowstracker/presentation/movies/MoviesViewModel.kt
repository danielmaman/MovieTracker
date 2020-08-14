package com.example.movieshowstracker.presentation.movies

import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.domain.DeleteFavoriteMovieFromDbUseCase
import com.example.movieshowstracker.domain.GetMovieByIDUseCase
import com.example.movieshowstracker.domain.InsertFavoriteMovieToDbUseCase
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import org.koin.core.KoinComponent

class MoviesViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val insertFavoriteMovieToDbUseCase: InsertFavoriteMovieToDbUseCase,
    private val deleteFavoriteMovieFromDbUseCase: DeleteFavoriteMovieFromDbUseCase,
    private val getMovieByIDUseCase: GetMovieByIDUseCase
) : ViewModel(), KoinComponent {

    fun fetchMovieList(searchString: String, cinematicType: CinematicType) =
        searchMoviesUseCase.execute(SearchMoviesUseCase.Request(searchString, cinematicType))

    fun favoriteButtonClicked(checked: Boolean, movie: Movie) =
        if (checked) insertFavoriteMovieToDbUseCase.execute(InsertFavoriteMovieToDbUseCase.Request(movie))
        else deleteFavoriteMovieFromDbUseCase.execute(DeleteFavoriteMovieFromDbUseCase.Request(movie))

    fun movieFieldExpanded(movie: Movie) = getMovieByIDUseCase.execute(GetMovieByIDUseCase.Request(movie.imdbID))

}