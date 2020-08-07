package com.example.movieshowstracker.data.repo

import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.data.model.PlotType
import com.example.movieshowstracker.data.services.MoviesService
import io.reactivex.Single
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesRepository : KoinComponent {//TODO implement through interface

    val movieService: MoviesService by inject()

    fun fetchMoviesByID(
        id: String,
        type: CinematicType?,
        year: Years?,
        plot: PlotType?
    ): Single<Movie> {
        return movieService.getMoviesByID(id, type, year, plot)
    }

    fun fetchMoviesByTitle(
        title: String,
        type: CinematicType?,
        year: Years?,
        plot: PlotType?
    ): Single<Movie> {
        return movieService.getMoviesByTitle(title, type, year, plot)
    }

    fun fetchMoviesBySearch(
        searchString: String,
        type: CinematicType?,
        year: Years?,
        page: Int?
    ): Single<MovieList> {
        return movieService.getMoviesBySearch(searchString, type, year, page)
    }

    fun getCachedMovieList() = ""
}