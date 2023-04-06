package com.example.moviefinal.data

import com.example.moviefinal.database.MovieEntiy
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("budget") val budget: Int,
    @SerializedName("poster_path") val poster: String,
) {
    fun toMovieEntity(): MovieEntiy {
        return MovieEntiy(id,title,releaseDate,budget,poster)
    }
}
