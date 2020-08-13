package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseCompletableUseCase
import com.example.movieshowstracker.base.BaseSingleUseCase
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Completable
import io.reactivex.Single
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class DeleteMovieFromDbUseCase : BaseCompletableUseCase<DeleteMovieFromDbUseCase.Request>(), KoinComponent {

    private val moviesRepository: MoviesRepository by inject()

    override fun create(request: Request): Completable {
        return moviesRepository.deleteMovie(request.movie)
    }

    data class Request(
       val movie: Movie
    ) : BaseCompletableUseCase.Request()
}