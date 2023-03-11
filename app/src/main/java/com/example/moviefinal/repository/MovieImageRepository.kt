package com.example.moviefinal.repository

import com.example.moviefinal.data.Movie
import com.example.moviefinal.data.MovieImages

interface MovieImageRepository {
    fun getMovieImage(movieId: Long, callback: Callback)

    interface Callback {
        fun onMovieImageLoaded(movieImages: MovieImages?)
    }
}