package com.example.moviefinal.database

import androidx.room.Dao
import androidx.room.Insert

import androidx.room.Query


@Dao
interface MovieDao {
    @Insert
    fun insert(movieEntiy: MovieEntiy)

    @Query("SELECT * FROM movieEntiy WHERE id = :movieId LIMIT 1")
    fun get(movieId: Long): MovieEntiy?

    @Query("SELECT * FROM movieEntiy")
    fun getList(): List<MovieEntiy?>
}