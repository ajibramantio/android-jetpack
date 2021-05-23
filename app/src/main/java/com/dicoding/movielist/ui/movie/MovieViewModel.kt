package com.dicoding.movielist.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class MovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getMovie(): LiveData<List<FilmEntity>> = filmRepository.getAllMovies()
}