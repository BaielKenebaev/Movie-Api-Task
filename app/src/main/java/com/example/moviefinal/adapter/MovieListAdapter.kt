package com.example.moviefinal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinal.R
import com.example.moviefinal.database.MovieEntiy


class MovieListAdapter(): RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    private var listMovie = mutableListOf<MovieEntiy>()



    class MovieListViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bindList(movieEntity: MovieEntiy){
            val idMovie: TextView = itemView.findViewById(R.id.id_movie)
            val titleMovie: TextView = itemView.findViewById(R.id.title_movie)

            idMovie.text = movieEntity.id.toString()
            titleMovie.text = movieEntity.title


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent,false))
    }

    override fun getItemCount(): Int {
        return listMovie.size

    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bindList(listMovie.get(position))
    }


    fun setData(list: List<MovieEntiy>){
        listMovie.clear()
        listMovie.addAll(list)
        notifyDataSetChanged()
    }

}