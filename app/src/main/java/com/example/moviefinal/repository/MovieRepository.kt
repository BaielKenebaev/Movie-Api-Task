package com.example.moviefinal.repository

import com.example.moviefinal.data.Movie

import com.example.moviefinal.database.MovieEntiy


interface MovieRepository {
    suspend fun getMovieDetails(movieId: Long): Movie?

    suspend fun getFromDataBase(): List<MovieEntiy>
}