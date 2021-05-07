package com.dicoding.movielist.ui.detail

import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    private lateinit var filmTitle: String

    fun setSelectedFilm(filmTitle: String) {
        this.filmTitle = filmTitle
    }

    fun getMovie(): FilmEntity = filmRepository.getMovie(filmTitle)

    fun getTvShow(): FilmEntity = filmRepository.getTvShow(filmTitle)
}