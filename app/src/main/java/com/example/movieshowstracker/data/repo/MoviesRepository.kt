package com.example.movieshowstracker.data.repo

import com.example.movieshowstracker.data.model.CinematicType
import com.example.movieshowstracker.data.model.Movie
import com.example.movieshowstracker.data.model.MovieList
import com.example.movieshowstracker.data.model.PlotType
import com.example.movieshowstracker.data.services.MoviesService
import org.joda.time.Years
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesRepository : KoinComponent {

    val movieService: MoviesService by inject()

    suspend fun getMoviesByID(
        id: String,
        type: CinematicType?,
        year: Years?,
        plot: PlotType?
    ): Movie {
        return movieService.getMoviesByID(id, type, year, plot)
    }

    suspend fun getMoviesByTitle(
        title: String,
        type: CinematicType?,
        year: Years?,
        plot: PlotType?
    ): Movie {
        return movieService.getMoviesByTitle(title, type, year, plot)
    }

    suspend fun getMoviesBySearch(
        searchString: String,
        type: CinematicType?,
        year: Years?,
        page: Int?
    ): MovieList {
        return movieService.getMoviesBySearch(searchString, type, year, page)
    }

//    suspend fun getLoginData(query: String): AllPeople {
//
//        return processDataFetchLogic(query)
//
//    }
//
//    suspend fun processDataFetchLogic(parameter:String): AllPeople{
//
//        for (x in 0 until 3){
//            println(" Data manipulation prior to REST API request if required $x")
//        }
//
//        val dataReceived = mLoginAPIService.getLoginData(parameter)
//
//        for (x in 0 until 3){
//            println(" Data manipulation post REST API request if required $x")
//        }
//
//        return dataReceived
//    }

}