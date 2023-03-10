package com.example.moviefinal

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinal.adapter.MovieImageAdapter
import com.example.moviefinal.data.Movie

import com.example.moviefinal.data.MovieImages
import com.example.moviefinal.presenter.MovieImagePresenter
import com.example.moviefinal.presenter.MovieImagePresenterImpl

import com.example.moviefinal.presenter.MoviePresenter
import com.example.moviefinal.presenter.MoviePresenterImpl
import com.example.moviefinal.view.MovieImageView
import com.example.moviefinal.view.MovieView

class MainActivity : AppCompatActivity(), MovieView, MovieImageView {
    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit) }
    private val loadButton: Button by lazy { findViewById(R.id.load_button) }
    private val movieTitle: TextView by lazy { findViewById(R.id.movie_title_value) }
    private val movieRelease: TextView by lazy { findViewById(R.id.movie_release_value) }
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value) }

    private val moviePoster: ImageView by lazy { findViewById(R.id.movie_poster) }

    private val movieRecyclerView: RecyclerView by lazy { findViewById(R.id.movie_rv) }

    private lateinit var movieImageAdapter: MovieImageAdapter

    private val moviePresenter: MoviePresenter by lazy { MoviePresenterImpl() }
    private  var  movieImagePresenter: MovieImagePresenter by lazy { MovieImagePresenterImpl() }


    override fun onCreate(savedInstanceState: Bundle?) {
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        moviePresenter.setView(this)
        movieImagePresenter.setImageVIew(this)
        //setRecyclerReview()
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movieImageAdapter = MovieImageAdapter(this)
        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()

            if(movieId != null){

                moviePresenter.onLoadClicked(movieId)
                movieImagePresenter.onImageLoadClicked(movieId)

            }

        }

    }


    override fun onStop() {
        super.onStop()
        moviePresenter.setView(null)
        movieImagePresenter.setImageVIew(null)
    }

    override fun setMovieImage(movieImages: MovieImages?){
        if (movieImages == null) {
            return
        }
        movieImageAdapter = MovieImageAdapter(this)
        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        movieRecyclerView.adapter = movieImageAdapter
        movieImageAdapter.setData(movieImages.posters)
    }

//    fun setRecyclerReview(){
//        //movieImageAdapter = MovieImageAdapter(this)
//        //movieRecyclerView.adapter = movieImageAdapter
//        movieRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//
//    }



    override fun setMovie(movie: Movie?){
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