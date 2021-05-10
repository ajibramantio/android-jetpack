package com.dicoding.movielist.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movielist.R
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.databinding.ActivityDetailFilmBinding
import com.dicoding.movielist.databinding.ContentDetailFilmBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory

class DetailFilmActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailContentBinding: ContentDetailFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailFilmBinding.detailContent

        setContentView(activityDetailFilmBinding.root)

        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_FILM)
            val type = extras.getString(EXTRA_TYPE)

            if (title != null) {
                viewModel.setSelectedFilm(title)

                if(type=="film"){
                    activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                    viewModel.getMovie().observe(this, { movies ->
                        activityDetailFilmBinding.progressBar.visibility = View.GONE
                        populateFilm(movies)
                    })
                } else {
                    activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                    viewModel.getTvShow().observe(this, { tvShows ->
                        activityDetailFilmBinding.progressBar.visibility = View.GONE
                        populateFilm(tvShows)
                    })
                }
            }
        }
    }

    private fun populateFilm(filmEntity: FilmEntity) {
        detailContentBinding.textTitle.text = filmEntity.title
        detailContentBinding.textDescription.text = filmEntity.overview
        detailContentBinding.textGenrefilm.text = filmEntity.genre
        detailContentBinding.textRating.text = StringBuilder("rating: " + filmEntity.imdbScore.toString() + "%")
        detailContentBinding.textDuration.text = StringBuilder("duration: " + filmEntity.duration.toString() + " minutes")
        detailContentBinding.textDate.text = filmEntity.releaseYear.toString()

        Glide.with(this)
            .load(filmEntity.photo)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                .error(R.drawable.ic_error))
            .into(detailContentBinding.imagePoster)

    }
}