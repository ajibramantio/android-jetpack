package com.dicoding.movielist.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.*
import com.dicoding.movielist.data.source.local.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM filmentities where type='movie' ")
    fun getMovie(): DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='movie' and favorited = 1")
    fun getFavoritedMovie():  DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='tvshow' ")
    fun getTvShow():  DataSource.Factory<Int, FilmEntity>

    @Query("SELECT * FROM filmentities where type='tvshow' and favorited = 1")
    fun getFavoritedTvShow(): DataSource.Factory<Int, FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(films: List<FilmEntity>)

    @Update
    fun updateFilm(film: FilmEntity)

    @Query("SELECT * FROM filmentities WHERE filmId = :filmId")
    fun getDetail(filmId: String): LiveData<FilmEntity>
}