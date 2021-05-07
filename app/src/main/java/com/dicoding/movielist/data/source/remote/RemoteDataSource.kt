package com.dicoding.movielist.data.source.remote

import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse
import com.dicoding.movielist.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): List<MovieResponse> = jsonHelper.loadMovies()

    fun getAllTvShows(): List<TvShowResponse> = jsonHelper.loadTvShows()

    fun getMovie(filmTitle: String) : MovieResponse = jsonHelper.loadMovie(filmTitle)

    fun getTvShow(filmTitle: String): TvShowResponse = jsonHelper.loadTvShow(filmTitle)
}