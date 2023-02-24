package com.example.techno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(
    private val movies: ArrayList<Movie>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, null)
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun addMovie(movie: Movie) {
        movies.add(movie)
        notifyItemInserted(movies.size - 1)
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView = itemView.findViewById(R.id.icon)
        private val title: TextView = itemView.findViewById(R.id.title)
        private val description: TextView = itemView.findViewById(R.id.description)

        fun bind(movie: Movie) {
            icon.setImageResource(movie.icon)
            title.text = movie.title
            description.text = movie.description
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}