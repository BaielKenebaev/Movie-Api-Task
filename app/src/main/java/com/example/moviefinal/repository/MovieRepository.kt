package com.example.moviefinal.repository

import com.example.moviefinal.data.Movie


interface MovieRepository {
    fun getMovieDetails(movieId: Long, callback: Callback)

    interface Callback {
        fun onMovieLoaded(movie: Movie?)
    }

}