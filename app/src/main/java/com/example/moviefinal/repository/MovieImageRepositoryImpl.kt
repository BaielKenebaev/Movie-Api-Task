package com.example.moviefinal.repository

import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.MovieImages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieImageRepositoryImpl: MovieImageRepository {
    override fun getMovieImage(movieId: Long, callback: MovieImageRepository.Callback) {
        MovieApi.INSTANCE.getMovieImage(movieId, API_KEY).enqueue(object : Callback<MovieImages> {
            override fun onResponse(call: Call<MovieImages>, response: Response<MovieImages>) {
                if(response.isSuccessful){
                    callback.onMovieImageLoaded(response.body())
                }
            }

            override fun onFailure(call: Call<MovieImages>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }
}