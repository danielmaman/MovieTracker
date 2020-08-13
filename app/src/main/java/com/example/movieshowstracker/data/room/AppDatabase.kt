package com.example.movieshowstracker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.room.dao.MovieDao

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}