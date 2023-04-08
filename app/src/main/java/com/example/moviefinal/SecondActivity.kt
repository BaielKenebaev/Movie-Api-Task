package com.example.moviefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviefinal.ViewModels.SavedViewModel
import com.example.moviefinal.adapter.MovieListAdapter
import com.example.moviefinal.database.MovieEntiy
import com.example.moviefinal.databinding.ActivitySecond2Binding
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecond2Binding
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecond2Binding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val secondViewModel: SavedViewModel by viewModels()
        secondViewModel.loadDataFromDataBase()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                secondViewModel.currentRoom.collect {
                    setList(it)
                    Log.d(MainActivity.TAG, "$it")

                    binding.listMovieRv
                }
            }

        }




    }

    override fun onStart() {
        super.onStart()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        movieListAdapter = MovieListAdapter()
        binding.listMovieRv.adapter = movieListAdapter
        binding.listMovieRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }


    fun setList(list: List<MovieEntiy>?) {
        if (list != null) {
            movieListAdapter.setData(list)
        }
    }



}