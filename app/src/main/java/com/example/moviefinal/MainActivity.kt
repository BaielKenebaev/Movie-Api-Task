package com.example.moviefinal

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.Movie
import com.example.moviefinal.data.MovieImage
import com.example.moviefinal.data.MovieImages
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    }

    override fun onStart() {
        super.onStart()
        setRecyclerReview()
        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){
                MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).enqueue(callbackGetMovieDetails)
                MovieApi.INSTANCE.getMovieImage(movieId, API_KEY).enqueue(callbackGetMovieImage)
            }

        }





    }

    private fun setMovieImage(movieImages: MovieImages?){
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

        movieTitle.text = movie.title
        movieRelease.text = movie.releaseDate
        movieBudget.text = movie.budget.toString()

        Glide
            .with(this)
            .load(Uri.parse("https://image.tmdb.org/t/p/original/${movie.poster}"))
            .fitCenter()
            .into(moviePoster)
    }



    private val callbackGetMovieDetails = object : Callback<Movie> {
        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            if (response.isSuccessful){
                setMovie(response.body())

            }
            else {

                Toast.makeText(applicationContext, "Failed" , Toast.LENGTH_SHORT).show()

            }
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
            Toast.makeText(applicationContext, "Failed" , Toast.LENGTH_SHORT).show()
        }

    }



    private val callbackGetMovieImage = object : Callback<MovieImages>{
        override fun onResponse(call: Call<MovieImages>, response: Response<MovieImages>) {
            if (response.isSuccessful){
                setMovieImage(response.body())

            }
        }

        override fun onFailure(call: Call<MovieImages>, t: Throwable) {
            TODO("Not yet implemented")
        }

    }




    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }
}