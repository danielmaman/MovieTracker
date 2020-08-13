package com.example.movieshowstracker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//TODO export room and database models to seperate module
@Entity
data class Movie(
    @PrimaryKey @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: Int,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Awards") val awards: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Metascore") val metascore: String,
    @ColumnInfo(name = "imdb_rating") @SerializedName("imdbRating") val imdbRating: Double,
    @ColumnInfo(name = "imdb_votes") @SerializedName("imdbVotes") val imdbVotes: String,
    @SerializedName("Type") val type: String,
    @SerializedName("DVD") val dVD: String,
    @ColumnInfo(name = "box_office") @SerializedName("BoxOffice") val boxOffice: String,
    @SerializedName("Production") val production: String,
    @SerializedName("Website") val website: String,
    @SerializedName("Response") val response: Boolean
)