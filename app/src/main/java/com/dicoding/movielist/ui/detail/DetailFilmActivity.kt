package com.dicoding.movielist.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.movielist.R
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.databinding.ActivityDetailFilmBinding
import com.dicoding.movielist.databinding.ContentDetailFilmBinding
import com.dicoding.movielist.viewmodel.ViewModelFactory
import com.dicoding.movielist.vo.Status

class DetailFilmActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FILM = "extra_film"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var detailContentBinding: ContentDetailFilmBinding
    private lateinit var activityDetailFilmBinding: ActivityDetailFilmBinding
    private lateinit var viewModel: DetailFilmViewModel

    private var type: String? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailFilmBinding.detailContent

        setContentView(activityDetailFilmBinding.root)

        setSupportActionBar(activityDetailFilmBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailFilmViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val title = extras.getString(EXTRA_FILM)
            type = extras.getString(EXTRA_TYPE)

            if (title != null) {
                viewModel.setSelectedFilm(title)
                if(type == "movie"){
                    viewModel.getMovie.observe(this, Observer{ film ->
                        if(film != null){
                            when(film.status){
                                Status.LOADING ->  activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (film.data != null) {
                                    activityDetailFilmBinding.progressBar.visibility = View.GONE
                                    populateFilm(film.data)
                                }
                                Status.ERROR ->   {
                                    activityDetailFilmBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }


                    })
                }
                else{
                    viewModel.getTvShow.observe(this, Observer{ film ->
                        if(film != null){
                            when(film.status){
                                Status.LOADING ->  activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                                Status.SUCCESS -> if (film.data != null) {
                                    activityDetailFilmBinding.progressBar.visibility = View.GONE
                                    populateFilm(film.data)
                                }
                                Status.ERROR ->   {
                                    activityDetailFilmBinding.progressBar.visibility = View.GONE
                                    Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                                }

                            }

                        }

                    })

                }

            }
        }
    }

    private fun populateFilm(filmEntity: FilmEntity) {

        detailContentBinding.apply {
            textTitle.text = filmEntity.title
            textDescription.text = filmEntity.overview
            textGenrefilm.text = filmEntity.genre
            textRating.text = StringBuilder("rating: " + filmEntity.imdbScore.toString() + "%")
            textDuration.text = StringBuilder("duration: " + filmEntity.duration.toString() + " minutes")
            textDate.text = filmEntity.releaseYear.toString()

            Glide.with(this@DetailFilmActivity)
                .load(filmEntity.photo)
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(imagePoster)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        this.menu = menu
        if(type == "movie"){
            viewModel.getMovie.observe(this, Observer{ film ->
                if(film != null){
                    when(film.status){
                        Status.LOADING ->  activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (film.data != null) {
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            val state = film.data.favorited
                            setFavoriteState(state)
                        }
                        Status.ERROR ->   {
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }

                    }

                }


            })
        }
        else{
            viewModel.getTvShow.observe(this, Observer{ film ->
                if(film != null){
                    when(film.status){
                        Status.LOADING ->  activityDetailFilmBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (film.data != null) {
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            val state = film.data.favorited
                            setFavoriteState(state)
                        }
                        Status.ERROR ->   {
                            activityDetailFilmBinding.progressBar.visibility = View.GONE
                            Toast.makeText(applicationContext, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            if(type == "movie"){
                viewModel.setFavoriteMovie()
            }
            else{
                viewModel.setFavoriteTv()
            }

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorited)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        }
    }
}