package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseCompletableUseCase
import com.example.movieshowstracker.data.model.FavoriteMovie
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.PlotType
import com.example.movieshowstracker.data.repo.MoviesRepository
import com.example.movieshowstracker.data.room.dao.FavoriteMovieDao
import io.reactivex.Completable
import org.koin.core.KoinComponent

class InsertFavoriteMovieToDbUseCase(private val moviesRepository: MoviesRepository) : BaseCompletableUseCase<InsertFavoriteMovieToDbUseCase.Request>(), KoinComponent {

    override fun create(request: Request): Completable {
        return moviesRepository.getMovieByIdFromDb(request.movie.imdbID)
            .onErrorResumeNext(
                moviesRepository.fetchMoviesByID(request.movie.imdbID, null, null, PlotType.FULL)
                    .doAfterSuccess { moviesRepository.insertMovie(it) })
            .doOnSubscribe {
                moviesRepository.insertFavoriteMovie(FavoriteMovie(request.movie.imdbID))
            }.ignoreElement()
    }

    data class Request(
       val movie: Movie
    ) : BaseCompletableUseCase.Request()
}