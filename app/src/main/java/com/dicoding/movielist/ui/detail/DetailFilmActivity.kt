package com.dicoding.movielist.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movielist.R
import com.dicoding.movielist.data.FilmEntity
import com.dicoding.movielist.databinding.ActivityDetailFilmBinding
import com.dicoding.movielist.databinding.ContentDetailFilmBinding

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

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailFilmViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_FILM)
            val type = extras.getString(EXTRA_TYPE)
            if (title != null) {
                viewModel.setSelectedFilm(title)
                if(type=="film"){
                    populateFilm(viewModel.getFilm())
                }
                else{
                    populateFilm(viewModel.getTvShow())
                }

            }
        }
    }
    private fun populateFilm(filmEntity: FilmEntity) {
        detailContentBinding.textTitle.text = filmEntity.title
        detailContentBinding.textDescription.text = filmEntity.overview
        detailContentBinding.textGenrefilm.text = filmEntity.genre
        detailContentBinding.textRating.text = "rating: " + filmEntity.imdbScore.toString() + "%"
        detailContentBinding.textDuration.text = "duration: " + filmEntity.duration.toString() + "minutes"
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