package com.example.moviefinal.repository

import com.example.moviefinal.data.Movie
import retrofit2.Call

interface MovieRepository {
    suspend fun getMovieDetails(movieId: Long): Movie?
}