package com.example.moviefinal

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.moviefinal.api.MovieApi
import com.example.moviefinal.data.Movie
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){
                MovieApi.INSTANCE.getMovieDetails(movieId, API_KEY).enqueue(callback)
            }

        }
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

    private val callback = object : Callback<Movie> {
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

    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }
}