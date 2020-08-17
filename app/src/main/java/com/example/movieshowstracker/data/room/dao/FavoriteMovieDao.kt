package com.example.movieshowstracker.data.room.dao

import androidx.room.*
import com.example.movieshowstracker.data.model.FavoriteMovie
import com.example.movieshowstracker.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movie")
    fun getAll(): Single<List<FavoriteMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteMovie: FavoriteMovie)

    @Delete
    fun delete(favoriteMovie: FavoriteMovie)
}