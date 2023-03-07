package com.example.moviefinal.presenter

import com.example.moviefinal.view.MovieView

interface MoviePresenter {
    fun setView(view: MovieView?)
    fun onLoadClicked(movieId: Long)

}