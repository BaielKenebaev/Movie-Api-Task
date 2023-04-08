package com.example.moviefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moviefinal.ViewModels.SavedViewModel
import com.example.moviefinal.data.Movie
import com.example.moviefinal.database.MovieEntiy
import com.example.moviefinal.databinding.ActivitySecond2Binding
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecond2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecond2Binding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val secondViewModel: SavedViewModel by viewModels()
        secondViewModel.loadDataFromDataBase()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                secondViewModel.currentRoom.collect {
                    setData(it)
                    Log.d(MainActivity.TAG, "$it")
                }
            }

        }




    }

    fun setData(roomData: List<MovieEntiy>?) {
        binding.roomText.text = roomData.toString()
    }


}