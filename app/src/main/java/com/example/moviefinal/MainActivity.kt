package com.example.moviefinal

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinal.ViewModels.MovieImageViewModel
import com.example.moviefinal.ViewModels.MovieViewModel
import com.example.moviefinal.data.Movie
import com.example.moviefinal.data.MovieImages




class MainActivity : AppCompatActivity() {
    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit) }
    private val loadButton: Button by lazy { findViewById(R.id.load_button) }
    private val movieTitle: TextView by lazy { findViewById(R.id.movie_title_value) }
    private val movieRelease: TextView by lazy { findViewById(R.id.movie_release_value) }
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value) }

    private val moviePoster: ImageView by lazy { findViewById(R.id.movie_poster) }

    private val movieRecyclerView: RecyclerView by lazy { findViewById(R.id.movie_rv) }

    private lateinit var movieImageAdapter: MovieImageAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "Activity -> onCreate() ")
    }

    override fun onStart() {
        super.onStart()

        val movieViewModel: MovieViewModel by viewModels()
        movieViewModel.currentMovie.observe(this) { movie -> setMovie(movie) }

        val movieImageViewModel: MovieImageViewModel by viewModels()
        movieImageViewModel.currentMovieImage.observe(this) {movieImage -> setMovieImage(movieImage)}


        setRecyclerReview()

        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){

                movieViewModel.loadMovie(movieId)
                movieImageViewModel.loadMovieImage(movieId)


            }

        }

    }


    fun setMovieImage(movieImages: MovieImages?){
        if (movieImages == null) {
            return
        }
        movieImageAdapter.setData(movieImages.posters)
    }

    private fun setRecyclerReview(){
        movieImageAdapter = MovieImageAdapter()
        movieRecyclerView.adapter = movieImageAdapter
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }



    fun setMovie(movie: Movie?){
        if (movie == null){
            return
        }

        Log.i(TAG, "Movie arrived -> $movie")

        movieTitle.text = movie.title
        movieRelease.text = movie.releaseDate
        movieBudget.text = movie.budget.toString()

        Glide
            .with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/original/${movie.poster}"))
            .fitCenter()
            .into(moviePoster)
    }

    companion object{
        const val TAG = "MainActivity"
    }

}