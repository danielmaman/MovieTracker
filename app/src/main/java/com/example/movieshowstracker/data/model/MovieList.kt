package com.example.movieshowstracker.data.model

import com.google.gson.annotations.SerializedName

data class MovieList(@SerializedName("Search") val movieList : List<Movie>)