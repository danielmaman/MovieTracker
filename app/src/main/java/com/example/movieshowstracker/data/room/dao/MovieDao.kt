package com.example.movieshowstracker.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.movieshowstracker.data.model.Movie
import io.reactivex.Completable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAll(): List<Movie>

    @Insert
    fun insert(movie: Movie): Completable

    @Delete
    fun delete(movie: Movie): Completable
}