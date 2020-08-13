package com.example.movieshowstracker.di

import androidx.room.Room
import com.example.movieshowstracker.data.room.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "movie-database"
        ).build()
    }
    single { get<AppDatabase>().movieDao() }
}