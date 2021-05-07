package com.dicoding.movielist.data

import com.dicoding.movielist.data.source.local.entity.FilmEntity

interface FilmDataSource {

    fun getAllMovies(): List<FilmEntity>

    fun getAllTvShows(): List<FilmEntity>

    fun getMovie(filmTitle: String): FilmEntity

    fun getTvShow(filmTitle: String): FilmEntity
}