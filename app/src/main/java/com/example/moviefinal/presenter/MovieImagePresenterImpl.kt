package com.example.moviefinal.repository

import com.example.moviefinal.MainActivity
import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.view.MovieImageView

class MovieImagePresenterImpl : MovieImagePresenter{
    private val repository: MovieImageRepository = MovieImageRepositoryImpl()

    private var view: MovieImageView? = null

    private val callback: MovieImageRepository.Callback = object : MovieImageRepository.Callback{
        override fun onMovieImageLoaded(movieImages: MovieImages?) {
            view?.setMovieImage(movieImages)
        }

    }

    override fun setImageView(view: MovieImageView?) {
        this.view = view
    }

    override fun onImageLoadClicked(movieId: Long) {
        repository.getMovieImage(movieId, callback)
    }


}