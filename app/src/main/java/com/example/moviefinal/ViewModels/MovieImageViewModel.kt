package com.example.moviefinal.ViewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.repository.MovieImageRepository
import com.example.moviefinal.repository.MovieImageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieImageViewModel: ViewModel() {
    private val _currentMovieImage = MutableStateFlow<MovieImages?>(null)
    private val repository: MovieImageRepository = MovieImageRepositoryImpl()





    val currentMovieImage: StateFlow<MovieImages?> = _currentMovieImage

    fun loadMovieImage(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieImages = repository.getMovieImage(movieId)
            _currentMovieImage.value = movieImages
        }
    }
}