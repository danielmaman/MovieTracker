package com.example.movieshowstracker.di

import com.example.movieshowstracker.domain.GetMovieByIDUseCase
import com.example.movieshowstracker.domain.SearchMoviesUseCase
import org.koin.dsl.module

val UseCaseModule = module {

    factory { SearchMoviesUseCase() }
    factory { GetMovieByIDUseCase() }

}