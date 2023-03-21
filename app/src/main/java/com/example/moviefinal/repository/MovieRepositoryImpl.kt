package com.example.moviefinal.repository

import com.example.moviefinal.MainActivity
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepositoryImpl.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getMovieDetails(movieId: Long): Movie?{
        val response = MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).execute()

        return if(response.isSuccessful){
            response.body()
        }
        else {
            null
        }


    }



    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }

}


