package com.example.moviefinal.data

import com.google.gson.annotations.SerializedName

data class MovieImage(
    @SerializedName("file_path") val filePath: String,
)
