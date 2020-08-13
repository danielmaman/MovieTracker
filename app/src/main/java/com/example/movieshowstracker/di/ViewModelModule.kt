package com.example.movieshowstracker.di

import com.example.movieshowstracker.presentation.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val ViewModelModule : Module = module {
    viewModel { MoviesViewModel(searchMoviesUseCase = get(), insertMovieToDbUseCase = get(), deleteMovieFromDbUseCase = get())}
}