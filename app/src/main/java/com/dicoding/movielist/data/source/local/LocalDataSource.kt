package com.dicoding.movielist.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovies():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getMovie()
    fun getAllTvShows():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getTvShow()

    fun getFavoritedFilm():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedMovie()
    fun getFavoritedTvShow():  DataSource.Factory<Int, FilmEntity> = mFilmDao.getFavoritedTvShow()

    fun getFilm(filmId: String): LiveData<FilmEntity> = mFilmDao.getDetail(filmId)

    fun insertFilm(films: List<FilmEntity>) = mFilmDao.insertMovie(films)

    fun updateFilm(film: FilmEntity){
        mFilmDao.updateFilm(film)
    }

    fun setFilmFavorite(film: FilmEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateFilm(film)
    }
}