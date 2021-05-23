package com.dicoding.movielist.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.movielist.data.source.local.entity.FilmEntity

@Dao
interface FilmDao {

    @Query("SELECT * FROM filmentities where type='movie' ")
    fun getMovie(): LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='movie' and favorited = 1")
    fun getFavoritedMovie():  LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='tvshow' ")
    fun getTvShow():  LiveData<List<FilmEntity>>

    @Query("SELECT * FROM filmentities where type='tvshow' and favorited = 1")
    fun getFavoritedTvShow(): LiveData<List<FilmEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(films: List<FilmEntity>)

    @Update
    fun updateMovie(film: FilmEntity)

    @Query("SELECT * FROM filmentities WHERE filmId = :filmId")
    fun getDetail(filmId: Int): LiveData<FilmEntity>
}