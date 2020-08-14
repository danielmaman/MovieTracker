package com.example.movieshowstracker.data.room.dao

import androidx.room.*
import com.example.movieshowstracker.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): Single<List<Movie>>

    @Query("SELECT * FROM movie where imdbID = :imdbId LIMIT 1")
    fun getMovie(imdbId: String): Single<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)
}