package com.dicoding.movielist.data

import androidx.lifecycle.LiveData
import com.dicoding.movielist.data.source.local.entity.FilmEntity

interface FilmDataSource {

    fun getAllMovies(): LiveData<List<FilmEntity>>

    fun getAllTvShows(): LiveData<List<FilmEntity>>

    fun getMovie(filmTitle: String): LiveData<FilmEntity>

    fun getTvShow(filmTitle: String): LiveData<FilmEntity>
}