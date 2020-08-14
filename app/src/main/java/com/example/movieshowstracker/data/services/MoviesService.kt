package com.example.movieshowstracker.data.services

import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.data.model.PlotType
import io.reactivex.Single
import org.joda.time.Years
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(".")
    fun getMoviesByID(
        @Query("i") id: String,
        @Query("type") type: CinematicType?,
        @Query("y") year: Years?,
        @Query("plot") plot: PlotType?
    ): Single<Movie>

    @GET(".")
    fun getMovieByTitle(
        @Query("t") title: String,
        @Query("type") type: CinematicType?,
        @Query("y") year: Years?,
        @Query("plot") plot: PlotType?
    ): Single<Movie>

    @GET(".")
    fun getMoviesBySearch(
        @Query("s") searchString: String,
        @Query("type") type: CinematicType?,
        @Query("y") year: Years?,
        @Query("page") page: Int? = 1
    ): Single<MovieList>
}