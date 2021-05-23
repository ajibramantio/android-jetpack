package com.dicoding.movielist.data.source.local

import androidx.lifecycle.LiveData
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.local.room.FilmDao

class LocalDataSource private constructor(private val mFilmDao: FilmDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao: FilmDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(filmDao)
    }

    fun getAllMovie():  LiveData<List<FilmEntity>> = mFilmDao.getMovie()
    fun getAllTvShow():  LiveData<List<FilmEntity>> = mFilmDao.getTvShow()

    fun getFavoritedFilm():  LiveData<List<FilmEntity>> = mFilmDao.getFavoritedMovie()
    fun getFavoritedTvShow():  LiveData<List<FilmEntity>> = mFilmDao.getFavoritedTvShow()

    fun getDetailFilm(filmId: Int): LiveData<FilmEntity> = mFilmDao.getDetail(filmId)

    fun insertFilm(films: List<FilmEntity>) = mFilmDao.insertMovie(films)

    fun updateFilm(film:FilmEntity){
        mFilmDao.updateMovie(film)
    }

    fun setFilmFavorite(film: FilmEntity, newState: Boolean) {
        film.favorited = newState
        mFilmDao.updateMovie(film)
    }
}