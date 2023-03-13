package com.example.moviefinal

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinal.ViewModels.MovieViewModel

import com.example.moviefinal.data.Movie

import com.example.moviefinal.data.MovieImages

import com.example.moviefinal.repository.MovieImagePresenter
import com.example.moviefinal.repository.MovieImagePresenterImpl
import com.example.moviefinal.view.MovieImageView


class MainActivity : AppCompatActivity(), MovieImageView{
    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit) }
    private val loadButton: Button by lazy { findViewById(R.id.load_button) }
    private val movieTitle: TextView by lazy { findViewById(R.id.movie_title_value) }
    private val movieRelease: TextView by lazy { findViewById(R.id.movie_release_value) }
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value) }

    private val moviePoster: ImageView by lazy { findViewById(R.id.movie_poster) }

    private val movieRecyclerView: RecyclerView by lazy { findViewById(R.id.movie_rv) }

    private lateinit var movieImageAdapter: MovieImageAdapter


    private val movieImagePresenter: MovieImagePresenter by lazy { MovieImagePresenterImpl() }


    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val movieViewModel: MovieViewModel by viewModels()
        movieViewModel.currentMovie.observe(this) { movie -> setMovie(movie) }
        movieImagePresenter.setImageView(this)
        setRecyclerReview()

        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){

                movieViewModel.loadMovie(movieId)
                movieImagePresenter.onImageLoadClicked(movieId)

            }

        }

    }


    override fun onStop() {
        super.onStop()

        movieImagePresenter.setImageView(null)
    }

    override fun setMovieImage(movieImages: MovieImages?){
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










    companion object {
        private const val API_KEY = "b4ed0cac530796fd0d402c1892784b22"
    }


}