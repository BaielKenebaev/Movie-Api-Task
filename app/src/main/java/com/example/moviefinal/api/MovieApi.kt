package com.example.moviefinal.api

import com.example.moviefinal.data.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Long,@Query("api_key") apiKey: String): Call<Movie>



    companion object {
        val INSTANCE = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
            .create(MovieApi::class.java)
    }
}