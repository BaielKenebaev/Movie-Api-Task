package com.example.moviefinal.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviefinal.database.MovieEntiy
import com.example.moviefinal.repository.MovieRepository
import com.example.moviefinal.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SavedViewModel(application: Application): AndroidViewModel(application) {
    private val repository: MovieRepository = MovieRepositoryImpl(application)
    private val _currentRoom = MutableStateFlow<List<MovieEntiy>?>(null)

    val currentRoom: StateFlow<List<MovieEntiy>?> = _currentRoom

    fun loadDataFromDataBase() {
        viewModelScope.launch(Dispatchers.IO) {
            val roomData = repository.getFromDataBase()
            _currentRoom.value = roomData
            }
        }
    }
