package com.example.moviefinal.presenter

import com.example.moviefinal.view.MovieImageView

interface MovieImagePresenter{
    fun setImageVIew(view: MovieImageView?)
    fun onImageLoadClicked(movieId: Long)
}