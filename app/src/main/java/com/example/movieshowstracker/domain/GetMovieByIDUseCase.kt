package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseSingleUseCase
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.PlotType
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Single
import org.koin.core.KoinComponent

class GetMovieByIDUseCase(private val moviesRepository: MoviesRepository) :
    BaseSingleUseCase<Movie, GetMovieByIDUseCase.Request>(), KoinComponent {

    override fun create(request: Request): Single<Movie> {
        return moviesRepository.getMovieByIdFromDb(request.imdbId)
            .onErrorResumeNext(
                moviesRepository.fetchMoviesByID(request.imdbId, null, null, PlotType.FULL)
                    .doAfterSuccess { moviesRepository.insertMovie(it) })
    }

    data class Request(
        val imdbId: String
    ) : BaseSingleUseCase.Request()
}