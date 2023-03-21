package com.example.moviefinal.repository
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.MovieImages


class MovieImageRepositoryImpl: MovieImageRepository {
    override suspend fun getMovieImage(movieId: Long): MovieImages? {
        val response = MovieApi.INSTANCE.getMovieImage(movieId, API_KEY).execute()
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