package com.example.moviefinal.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefinal.data.MovieImage
import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.repository.MovieImageRepository
import com.example.moviefinal.repository.MovieImageRepositoryImpl

class MovieImageViewModel: ViewModel() {
    private val _currentMovieImage: MutableLiveData<MovieImages?> = MutableLiveData(null)
    private val repository: MovieImageRepository = MovieImageRepositoryImpl()
    private val callback: MovieImageRepository.Callback = object : MovieImageRepository.Callback{
        override fun onMovieImageLoaded(movieImages: MovieImages?) {
            _currentMovieImage.value = movieImages
        }
    }

    val currentMovieImage: MutableLiveData<MovieImages?> = _currentMovieImage

    fun loadMovieImage(movieId: Long){
        repository.getMovieImage(movieId, callback)
    }
}