package com.example.movieshowstracker.data.repo

import com.example.movieshowstracker.data.model.*
import com.example.movieshowstracker.data.room.dao.FavoriteMovieDao
import com.example.movieshowstracker.data.room.dao.MovieDao
import com.example.movieshowstracker.data.services.MoviesService
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.joda.time.Years
import org.koin.core.KoinComponent

class MoviesRepository(private val movieService: MoviesService, private val movieDao: MovieDao, private val favoriteMovieDao: FavoriteMovieDao) : KoinComponent {//TODO implement through interface


    fun fetchMoviesByID(
        id: String,
        type: CinematicType?,
        year: Years?,
        plot: PlotType?
    ): Single<Movie> {
        return movieService.getMoviesByID(id, type, year, plot)
    }

    fun fetchMoviesBySearch(
        searchString: String,
        type: CinematicType?,
        year: Years?,
        page: Int?
    ): Single<MovieList> {
        return movieService.getMoviesBySearch(searchString, type, year, page)
    }

    fun getMovieByIdFromDb(imdbId: String): Single<Movie> {
        return movieDao.getMovie(imdbId)
    }

    fun insertMovie(movie: Movie) {
        return movieDao.insert(movie)
    }

    fun getFavoriteMovies(): Single<List<FavoriteMovie>> {
        return favoriteMovieDao.getAll()
    }

    fun insertFavoriteMovie(favoriteMovie: FavoriteMovie){
        return favoriteMovieDao.insert(favoriteMovie)
    }

    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovie){
        return favoriteMovieDao.delete(favoriteMovie)
    }
}