package com.dicoding.movielist.ui.movie

import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.utils.DataDummy

class MovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getMovie(): List<FilmEntity> = filmRepository.getAllMovies()
}