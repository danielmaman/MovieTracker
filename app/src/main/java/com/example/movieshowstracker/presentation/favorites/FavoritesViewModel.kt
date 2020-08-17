package com.example.movieshowstracker.presentation.favorites

import androidx.lifecycle.ViewModel
import com.example.movieshowstracker.domain.*
import org.koin.core.KoinComponent

class FavoritesViewModel(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : ViewModel(), KoinComponent {

    fun getFavoriteMovieList() = getFavoriteMoviesUseCase.execute(GetFavoriteMoviesUseCase.Request())

}