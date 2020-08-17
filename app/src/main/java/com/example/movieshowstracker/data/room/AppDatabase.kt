package com.example.movieshowstracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movieshowstracker.data.model.FavoriteMovie
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.room.dao.FavoriteMovieDao
import com.example.movieshowstracker.data.room.dao.MovieDao

@Database(entities = arrayOf(Movie::class, FavoriteMovie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun favoriteDao(): FavoriteMovieDao
}