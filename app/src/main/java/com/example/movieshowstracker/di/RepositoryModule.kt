package com.example.movieshowstracker.di

import com.example.movieshowstracker.data.repo.MoviesRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val RepositoryModule : Module = module {
    factory { MoviesRepository() }
}