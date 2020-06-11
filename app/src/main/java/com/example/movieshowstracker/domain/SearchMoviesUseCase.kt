package com.example.movieshowstracker.domain

import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.data.repo.MoviesRepository
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class SearchMoviesUseCase: KoinComponent {

    val moviesRepository : MoviesRepository by inject()

    suspend fun getMoviesBySearch(
        searchString: String,
        type: CinematicType? = null,
        year: Years? = null,
        page: Int = 1
    ): MovieList {
        for (x in 0 until 3){
            println(" Pre Data manipulation $x")
        }
        val response =  moviesRepository.getMoviesBySearch(searchString, type, year, page)

        for (x in 0 until 3){
            println(" Post Data manipulation $x")
        }

        return response
    }

//    suspend fun processLoginUseCase(query: String) : AllPeople {
//        for (x in 0 until 3){
//            println(" Pre Data manipulation $x")
//        }
//        val response =  mLoginRepo.getLoginData(query)
//
//        for (x in 0 until 3){
//            println(" Post Data manipulation $x")
//        }
//
//        return response
//    }
}