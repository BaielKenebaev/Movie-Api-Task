package com.example.moviefinal.repository

import com.example.moviefinal.data.Movie
import com.example.moviefinal.database.MovieEntiy
import retrofit2.Call

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Long): Movie?

    suspend fun getFromDataBase(): List<MovieEntiy>
}