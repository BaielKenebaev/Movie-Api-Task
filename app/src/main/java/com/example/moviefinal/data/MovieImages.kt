package com.example.moviefinal.data

import com.google.gson.annotations.SerializedName

data class MovieImages(
    @SerializedName("backdrops") val backdrops: List<MovieImage>,
    @SerializedName("posters") val posters: List<MovieImage>,
)
