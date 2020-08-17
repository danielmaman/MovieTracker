package com.example.movieshowstracker.di

import com.example.movieshowstracker.domain.*
import org.koin.dsl.module

val UseCaseModule = module {

    factory { SearchMoviesUseCase(get()) }
    factory { GetMovieByIDUseCase(get()) }
    factory { InsertFavoriteMovieToDbUseCase(get()) }
    factory { DeleteFavoriteMovieFromDbUseCase(get()) }
    factory { GetFavoriteMoviesUseCase(get()) }
}