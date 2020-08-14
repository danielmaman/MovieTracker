package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseCompletableUseCase
import com.example.movieshowstracker.data.model.FavoriteMovie
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Completable
import org.koin.core.KoinComponent

class DeleteFavoriteMovieFromDbUseCase(private val moviesRepository: MoviesRepository) : BaseCompletableUseCase<DeleteFavoriteMovieFromDbUseCase.Request>(), KoinComponent {

    override fun create(request: Request): Completable {
        return Completable.fromAction {
            moviesRepository.deleteFavoriteMovie(FavoriteMovie(request.movie.imdbID))
        }
    }

    data class Request(
       val movie: Movie
    ) : BaseCompletableUseCase.Request()
}