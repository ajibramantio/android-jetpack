package com.dicoding.movielist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse

class FakeFilmRepository (private val remoteDataSource: RemoteDataSource) : FilmDataSource {

    override fun getAllMovies(): LiveData<List<FilmEntity>> {
        val movieResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in movieResponses) {
                    val movie = FilmEntity(
                        response.id,
                        response.title,
                        response.genre,
                        response.overview,
                        response.imdbScore,
                        response.releaseYear,
                        response.duration,
                        response.photo,
                        response.favorited,
                        response.type
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<FilmEntity>> {
        val tvShowResults = MutableLiveData<List<FilmEntity>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvShowResponse>) {
                val tvShowList = ArrayList<FilmEntity>()
                for (response in tvShowResponses) {
                    val tvShow = FilmEntity(
                        response.id,
                        response.title,
                        response.genre,
                        response.overview,
                        response.imdbScore,
                        response.releaseYear,
                        response.duration,
                        response.photo,
                        response.favorited,
                        response.type
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getMovie(filmTitle: String): LiveData<FilmEntity> {
        val movieResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getMovie(filmTitle, object : RemoteDataSource.LoadMovieCallback {
            override fun onMovieReceived(movieResponse: MovieResponse) {
                val movie = FilmEntity(
                    movieResponse.id,
                    movieResponse.title,
                    movieResponse.genre,
                    movieResponse.overview,
                    movieResponse.imdbScore,
                    movieResponse.releaseYear,
                    movieResponse.duration,
                    movieResponse.photo,
                    movieResponse.favorited,
                    movieResponse.type
                )
                movieResult.postValue(movie)
            }
        })

        return movieResult
    }

    override fun getTvShow(filmTitle: String): LiveData<FilmEntity> {
        val tvShowResult = MutableLiveData<FilmEntity>()
        remoteDataSource.getTvShow(filmTitle, object : RemoteDataSource.LoadTvShowCallback {
            override fun onTvShowReceived(tvShowResponse: TvShowResponse) {
                val tvShow = FilmEntity(
                    tvShowResponse.id,
                    tvShowResponse.title,
                    tvShowResponse.genre,
                    tvShowResponse.overview,
                    tvShowResponse.imdbScore,
                    tvShowResponse.releaseYear,
                    tvShowResponse.duration,
                    tvShowResponse.photo,
                    tvShowResponse.favorited,
                    tvShowResponse.type
                )
                tvShowResult.postValue(tvShow)
            }
        })

        return tvShowResult
    }
}