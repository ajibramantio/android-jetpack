package com.dicoding.movielist.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmEntity
import com.dicoding.movielist.utils.DataDummy

class DetailFilmViewModel: ViewModel() {
    private lateinit var filmTitle: String

    fun setSelectedFilm(filmTitle: String) {
        this.filmTitle = filmTitle
    }


    fun getFilm(): FilmEntity {
        lateinit var film: FilmEntity
        val filmEntities = DataDummy.generateDummyMovie()
        for (filmEntity in filmEntities) {
            if (filmEntity.title == filmTitle) {
                film = filmEntity
            }
        }
        return film
    }
    fun getTvShow(): FilmEntity {
        lateinit var tvshow: FilmEntity
        val tvshowEntities = DataDummy.generateDummyTvShows()
        for (tvshowEntity in tvshowEntities) {
            if (tvshowEntity.title == filmTitle) {
                tvshow = tvshowEntity
            }
        }
        return tvshow
    }


}