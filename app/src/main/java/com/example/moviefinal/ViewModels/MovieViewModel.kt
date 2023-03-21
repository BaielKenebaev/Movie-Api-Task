package com.example.moviefinal.ViewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepository
import com.example.moviefinal.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    private val _currentMovie = MutableStateFlow<Movie?>(null)
    private val repository: MovieRepository = MovieRepositoryImpl()


    val currentMovie: StateFlow<Movie?> = _currentMovie


    fun loadMovie(movieId: Long){
        viewModelScope.launch(Dispatchers.IO) {
            val movie = repository.getMovieDetails(movieId)
            _currentMovie.value = movie
        }
    }
}