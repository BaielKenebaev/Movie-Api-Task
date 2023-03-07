package com.example.moviefinal.presenter

import com.example.moviefinal.data.Movie
import com.example.moviefinal.repository.MovieRepository
import com.example.moviefinal.repository.MovieRepositoryImpl
import com.example.moviefinal.view.MovieView

class MoviePresenterImpl: MoviePresenter {
    private val repository: MovieRepository = MovieRepositoryImpl()

    private val callback: MovieRepository.Callback = object : MovieRepository.Callback{
        override fun onMovieLoaded(movie: Movie?) {
            view?.setMovie(movie)
        }

    }

    private var view: MovieView? = null

    override fun setView(view: MovieView?) {
        this.view = view
    }

    override fun onLoadClicked(movieId: Long) {
       repository.getMovieDetails(movieId, callback)
    }


}