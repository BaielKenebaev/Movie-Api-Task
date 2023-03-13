package com.example.moviefinal.ViewModels

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepository
import com.example.moviefinal.repository.MovieRepositoryImpl

class MovieViewModel: ViewModel() {
    private val _currentMovie: MutableLiveData<Movie?> = MutableLiveData(null)
    private val repository: MovieRepository = MovieRepositoryImpl()
    private val callback: MovieRepository.Callback = object : MovieRepository.Callback{
        override fun onMovieLoaded(movie: Movie?) {
            _currentMovie.value = movie
        }

    }

    val currentMovie: LiveData<Movie?> = _currentMovie


    fun loadMovie(movieId: Long){
        repository.getMovieDetails(movieId, callback)
    }
}