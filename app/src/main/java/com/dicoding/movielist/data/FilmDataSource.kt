package com.dicoding.movielist.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.vo.Resource

interface FilmDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<FilmEntity>>>

    fun getAllTvShows(): LiveData<Resource<PagedList<FilmEntity>>>

    fun getMovie(filmId: String): LiveData<Resource<FilmEntity>>

    fun getTvShow(filmId: String): LiveData<Resource<FilmEntity>>

    fun getFavoritedFilm(): LiveData<PagedList<FilmEntity>>

    fun getFavoritedTvShow(): LiveData<PagedList<FilmEntity>>

    fun setFavoriteFilm(film: FilmEntity, state: Boolean)

    fun setFavoriteTvShow(tv: FilmEntity, state: Boolean)
}