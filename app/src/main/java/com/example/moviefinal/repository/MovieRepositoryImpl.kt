package com.example.moviefinal.repository

import com.example.moviefinal.MainActivity
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepositoryImpl.Companion.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImpl : MovieRepository {
    override fun getMovieDetails(movieId: Long, callback: MovieRepository.Callback) {
       MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).enqueue(object : Callback<Movie> {
           override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
               if(response.isSuccessful){
                   callback.onMovieLoaded(response.body())
               }
               else {
                   callback.onMovieLoaded((null))
               }
           }

           override fun onFailure(call: Call<Movie>, t: Throwable) {
               TODO("Not yet implemented")
           }

       })


    }



    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }

}


