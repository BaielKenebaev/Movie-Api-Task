package com.example.moviefinal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.moviefinal.data.Movie


@Entity
data class MovieEntiy(
    @PrimaryKey(autoGenerate = false) val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "budget") val budget: Int,
    @ColumnInfo(name = "poster") val poster: String,
) {
    @Ignore
    fun toMovie(): Movie {
        return Movie(id, title, releaseDate, budget, poster)
    }




}