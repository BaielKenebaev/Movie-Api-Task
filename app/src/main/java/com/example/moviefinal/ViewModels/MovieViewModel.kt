package com.example.moviefinal.ViewModels


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepository
import com.example.moviefinal.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(application: Application): AndroidViewModel(application) {
    private val _currentMovie = MutableStateFlow<Movie?>(null)
    private val repository: MovieRepository = MovieRepositoryImpl(application)


    val currentMovie: StateFlow<Movie?> = _currentMovie


    fun loadMovie(movieId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val movie = repository.getMovieDetails(movieId)
            _currentMovie.value = movie
        }
    }
}