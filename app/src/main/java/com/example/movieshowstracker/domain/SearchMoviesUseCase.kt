package com.example.movieshowstracker.domain

import com.example.movieshowstracker.base.BaseSingleUseCase
import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.FavoriteMovie
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.data.repo.MoviesRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import org.joda.time.Years
import org.koin.core.KoinComponent

class SearchMoviesUseCase(private val moviesRepository: MoviesRepository) :
    BaseSingleUseCase<List<Movie>, SearchMoviesUseCase.Request>(), KoinComponent {

    override fun create(request: Request): Single<List<Movie>> {
        return Single.zip(fetchMovies(request), getFavoriteMovies(), BiFunction { t1, t2 -> map(t1.movieList ?: arrayListOf(), t2 ?: arrayListOf()) })
    }

    private fun map(movies: List<Movie>, favoriteMovies: List<FavoriteMovie>): List<Movie> {
        movies.forEach { movie ->
            favoriteMovies.forEach { favoriteMovies ->
                if (movie.imdbID == favoriteMovies.imdbID){
                    movie.isFavorite = true
                }
            }
        }
        return movies
    }

    private fun fetchMovies(request: Request): Single<MovieList> {
        return moviesRepository.fetchMoviesBySearch(
            request.searchString,
            request.type,
            request.year,
            request.page
        )
    }

    private fun getFavoriteMovies(): Single<List<FavoriteMovie>> {
        return moviesRepository.getFavoriteMovies()
    }

    data class Request(
        val searchString: String,
        val type: CinematicType? = null,
        val year: Years? = null,
        val page: Int = 1
    ) : BaseSingleUseCase.Request()
}