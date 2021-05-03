package com.dicoding.movielist.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movielist.R
import com.dicoding.movielist.databinding.ItemsTvshowBinding
import com.dicoding.movielist.ui.detail.DetailFilmActivity
import com.dicoding.movielist.data.FilmEntity

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var listTvShows = ArrayList<FilmEntity>()

    fun setTvShow(tvshows: List<FilmEntity>?) {
        if (tvshows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvshows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TvShowViewHolder {
        val itemsTvShowBinding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val film = listTvShows[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int = listTvShows.size
    class TvShowViewHolder(private val binding: ItemsTvshowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: FilmEntity) {
            with(binding) {
                tvItemTitle.text = film.title
                tvItemDate.text =film.releaseYear.toString()
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, film.title)
                    intent.putExtra(DetailFilmActivity.EXTRA_TYPE, "tvshow")
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