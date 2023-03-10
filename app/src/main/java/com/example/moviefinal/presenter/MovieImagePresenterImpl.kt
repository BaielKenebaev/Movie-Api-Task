package com.example.moviefinal.presenter

import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.repository.MovieImageRepository
import com.example.moviefinal.repository.MovieImageRepositoryImpl
import com.example.moviefinal.repository.MovieRepositoryImpl
import com.example.moviefinal.view.MovieImageView
import com.example.moviefinal.view.MovieView

class MovieImagePresenterImpl: MovieImagePresenter {
    private val repository: MovieImageRepository = MovieImageRepositoryImpl()

    private var view: MovieImageView? = null

    private val callback: MovieImageRepository.Callback = object : MovieImageRepository.Callback{
        override fun onMovieImageLoaded(movieImages: MovieImages?) {
            view?.setMovieImage(movieImages)
        }
    }


    override fun setImageVIew(view: MovieImageView?) {
        this.view = view
    }

    override fun onImageLoadClicked(movieId: Long) {
        repository.getMovieImage(movieId, callback)
    }


}