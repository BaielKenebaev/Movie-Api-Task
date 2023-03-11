package com.example.moviefinal.repository

import com.example.moviefinal.view.MovieImageView

interface MovieImagePresenter {
    fun setImageView(view: MovieImageView?)
    fun onImageLoadClicked(movieId: Long)
}