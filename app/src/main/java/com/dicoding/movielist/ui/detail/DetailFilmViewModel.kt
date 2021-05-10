package com.dicoding.movielist.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    private lateinit var filmTitle: String

    fun setSelectedFilm(filmTitle: String) {
        this.filmTitle = filmTitle
    }

    fun getMovie(): LiveData<FilmEntity> = filmRepository.getMovie(filmTitle)

    fun getTvShow(): LiveData<FilmEntity> = filmRepository.getTvShow(filmTitle)
}