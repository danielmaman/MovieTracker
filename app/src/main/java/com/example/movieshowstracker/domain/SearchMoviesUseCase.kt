package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseUseCase
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Single
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchMoviesUseCase : BaseUseCase<List<Movie>, SearchMoviesUseCase.Request>(), KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    override fun create(request: Request): Single<List<Movie>> {
        return moviesRepository.fetchMoviesBySearch(
            request.searchString,
            request.type,
            request.year,
            request.page
        )
            .map {
                it.movieList
            }
    }

    data class Request(
        val searchString: String,
        val type: CinematicType? = null,
        val year: Years? = null,
        val page: Int = 1
    ) : BaseUseCase.Request()
}