package com.example.movieshowstracker.domain

import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.PlotType
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Single
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieByIDUseCase: KoinComponent {

    private val moviesRepository : MoviesRepository by inject()

    fun getMoviesById(
        id: String,
        type: CinematicType? = null,
        year: Years? = null,
        plot: PlotType? = null
    ): Single<Movie> {
        return moviesRepository.fetchMoviesByID(id, type, year, plot)
    }
}