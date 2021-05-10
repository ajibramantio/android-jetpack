package com.dicoding.movielist.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movielist.R
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.databinding.ItemsMovieBinding
import com.dicoding.movielist.ui.detail.DetailFilmActivity

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.FilmViewHolder>() {

    private var listFilms = ArrayList<FilmEntity>()

    fun setFilm(films: List<FilmEntity>?) {
        if (films.isNullOrEmpty()) return
        this.listFilms.clear()
        this.listFilms.addAll(films)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemsFilmBinding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(itemsFilmBinding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = listFilms.size
    class FilmViewHolder(private val binding: ItemsMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmEntity) {
            with(binding) {
                tvItemTitle.text = film.title
                tvItemDate.text =film.releaseYear.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, film.title)
                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "film")
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(film.photo)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}