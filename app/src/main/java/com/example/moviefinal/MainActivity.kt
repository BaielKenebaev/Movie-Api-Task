package com.example.moviefinal

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.moviefinal.ViewModels.MovieImageViewModel
import com.example.moviefinal.ViewModels.MovieViewModel
import com.example.moviefinal.data.Movie
import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.databinding.ActivityMainBinding
import kotlinx.coroutines.launch




class MainActivity : AppCompatActivity() {

    private lateinit var movieImageAdapter: MovieImageAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val movieViewModel: MovieViewModel by viewModels()
        val movieImageViewModel: MovieImageViewModel by viewModels()
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("default", Context.MODE_PRIVATE)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                movieViewModel.currentMovie.collect{
                    setMovie(it)
                    Log.d(TAG, "$it")
                }
            }

        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieImageViewModel.currentMovieImage.collect{
                    setMovieImage(it)
                    Log.d(TAG, "$it")
                }
            }
        }

        binding.loadButton.setOnClickListener {
            val movieId =binding.movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){

                movieViewModel.loadMovie(movieId)
                preferences.edit().putLong("movieId", movieId).apply()
                movieImageViewModel.loadMovieImage(movieId)

            }

        }

        if(preferences.getLong("movieId", Long.MAX_VALUE) != Long.MIN_VALUE){
            movieViewModel.loadMovie(preferences.getLong("movieId", Long.MIN_VALUE))
        }


        binding.roomButton.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }


    }



    override fun onStart() {
        super.onStart()

        setRecyclerReview()

    }


    fun setMovieImage(movieImages: MovieImages?){
        if (movieImages == null) {
            return
        }
        movieImageAdapter.setData(movieImages.posters)
    }

    private fun setRecyclerReview(){
        movieImageAdapter = MovieImageAdapter()
        binding.movieRv.adapter = movieImageAdapter
        binding.movieRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }



    fun setMovie(movie: Movie?){
        if (movie == null){
            return
        }

        Log.i(TAG, "Movie arrived -> $movie")

        binding.movieTitleValue.text = movie.title
        binding.movieReleaseValue.text = movie.releaseDate
        binding.movieBudgetValue.text = movie.budget.toString()

        Glide
            .with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/original/${movie.poster}"))
            .fitCenter()
            .into(binding.moviePoster)
    }

    companion object{
        const val TAG = "MainActivity_SET_MOVIE"
        const val TAG_PREF = "PREF_SHARE_COUNT"
    }

}