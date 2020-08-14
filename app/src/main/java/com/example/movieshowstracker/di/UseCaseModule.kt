package com.example.movieshowstracker.di

import com.example.movieshowstracker.domain.DeleteFavoriteMovieFromDbUseCase
import com.example.movieshowstracker.domain.GetMovieByIDUseCase
import com.example.movieshowstracker.domain.InsertFavoriteMovieToDbUseCase
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory { SearchMoviesUseCase(get()) }
    factory { GetMovieByIDUseCase(get()) }
    factory { InsertFavoriteMovieToDbUseCase(get()) }
    factory { DeleteFavoriteMovieFromDbUseCase(get()) }

}