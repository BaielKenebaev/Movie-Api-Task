package com.example.moviefinal

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinal.data.Movie
import com.example.moviefinal.data.MovieImage
import com.example.moviefinal.data.MovieImages

class MovieImageAdapter(): RecyclerView.Adapter<MovieImageAdapter.MovieImageViewHolder>() {
    private var movieImage = mutableListOf<MovieImage>()


    class MovieImageViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindMovieImage(movieImage: MovieImage){
           itemView
            Glide
                .with(itemView)
                .load(Uri.parse("https://image.tmdb.org/t/p/original/${movieImage.filePath}"))
                .into(itemView.findViewById(R.id.movie_image_item))
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImageViewHolder {
        return MovieImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false))
    }


    override fun onBindViewHolder(holder: MovieImageViewHolder, position: Int) {
        holder.bindMovieImage(movieImage.get(position))
    }


    override fun getItemCount(): Int = movieImage.size



    fun setData(listMovie: List<MovieImage>){
        //movieImage = listMovie as MutableList<MovieImage>
        movieImage.clear()
        movieImage.addAll(listMovie )
        notifyDataSetChanged()
    }

}