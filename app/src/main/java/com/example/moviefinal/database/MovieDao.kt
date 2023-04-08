package com.example.moviefinal.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import com.example.moviefinal.data.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


@Dao
interface MovieDao {


    @Query("SELECT * FROM movieEntiy")
    fun getListMovie(): List<MovieEntiy>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movieEntiy: MovieEntiy)

    @Query("SELECT * FROM movieEntiy WHERE id = :movieId LIMIT 1")
    fun get(movieId: Long): MovieEntiy?

}