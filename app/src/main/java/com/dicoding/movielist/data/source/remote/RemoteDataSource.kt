package com.dicoding.movielist.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse
import com.dicoding.movielist.utils.EspressoIdlingResource
import com.dicoding.movielist.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<FilmEntity>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<FilmEntity>>>()
        handler.postDelayed({
            resultMovie.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMovie
    }

    fun getAllTvShows(): LiveData<ApiResponse<List<FilmEntity>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<FilmEntity>>>()
        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTvShow
    }

    fun getMovie(filmTitle: String): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val resultMov = MutableLiveData<ApiResponse<MovieResponse>>()
        handler.postDelayed({
            resultMov.value = ApiResponse.success(jsonHelper.loadMovie(filmTitle))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultMov
    }

    fun getTvShow(filmTitle: String): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvShowResponse>>()
        handler.postDelayed({
            resultTv.value = ApiResponse.success(jsonHelper.loadTvShow(filmTitle))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultTv
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieResponse>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>)
    }

    interface LoadMovieCallback {
        fun onMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadTvShowCallback {
        fun onTvShowReceived(tvShowResponse: TvShowResponse)
    }
}