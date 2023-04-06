package com.example.moviefinal.repository

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.Movie
import com.example.moviefinal.database.MovieDatabase


class MovieRepositoryImpl(
    private val context: Context
) : MovieRepository {

    private val database = Room
        .databaseBuilder(context, MovieDatabase::class.java, "movie_database")
        .build()

    override suspend fun getMovieDetails(movieId: Long): Movie?{

        val savedMovieEntiy = database.movieDao().get(movieId)

        return if (savedMovieEntiy != null) {
            Log.d("DATABASE_BASE", "DATABASE")
            savedMovieEntiy.toMovie()
        } else {
            val response = MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).execute()
            if (response.isSuccessful){
                val movie = response.body()
                Log.d("DATABASE_BASE", "SERVER")
                if(movie != null) {
                    database.movieDao().insert(movie.toMovieEntity())
                }
                movie
            }
            else {
                null

            }
        }
    }




    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }

}


