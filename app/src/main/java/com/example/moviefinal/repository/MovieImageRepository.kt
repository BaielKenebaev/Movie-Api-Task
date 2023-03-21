package com.example.moviefinal.repository



import com.example.moviefinal.data.MovieImages

interface MovieImageRepository {
    suspend fun getMovieImage(movieId: Long): MovieImages?


}