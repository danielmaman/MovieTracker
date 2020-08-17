package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseSingleUseCase
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Single
import org.koin.core.KoinComponent

class GetFavoriteMoviesUseCase(private val moviesRepository: MoviesRepository) :
    BaseSingleUseCase<List<Movie>, GetFavoriteMoviesUseCase.Request>(), KoinComponent {
    //TODO fetch from web if database is empty
    override fun create(request: Request): Single<List<Movie>> {
        return moviesRepository.getFavoriteMovies()
            .onErrorReturn { arrayListOf() }
            .map { it.map { it.imdbID } }
            .flatMap {
                moviesRepository.getMoviesByIdsFromDb(it)
            }
            .map { it.map { it.apply { isFavorite = true } } }
    }


    class Request : BaseSingleUseCase.Request()
}