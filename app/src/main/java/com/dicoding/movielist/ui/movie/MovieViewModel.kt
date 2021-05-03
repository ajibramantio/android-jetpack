package com.dicoding.movielist.ui.movie

import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmEntity
import com.dicoding.movielist.utils.DataDummy

class MovieViewModel: ViewModel() {
    fun getMovie(): List<FilmEntity> = DataDummy.generateDummyMovie()
}