@file:Suppress("DEPRECATION")

package com.dicoding.movielist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.movielist.data.source.local.LocalDataSource
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.ApiResponse
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse
import com.dicoding.movielist.utils.AppExecutors
import com.dicoding.movielist.vo.Resource

class FakeFilmRepository constructor(private val remoteDataSource: RemoteDataSource,
                                         private val localDataSource: LocalDataSource,
                                         private val appExecutors: AppExecutors) :
    FilmDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(data: List<FilmEntity>) {
                val movieList = ArrayList<FilmEntity>()
                for (response in data) {
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
                localDataSource.insertFilm(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<FilmEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<FilmEntity>>> =
                remoteDataSource.getAllTvShows()

            public override fun saveCallResult(data: List<FilmEntity>) {
                val tvShowList = ArrayList<FilmEntity>()
                for (response in data) {
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
                localDataSource.insertFilm(tvShowList)
            }
        }.asLiveData()
    }

    override fun getMovie(filmId: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, MovieResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getFilm(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data?.overview ==""

            public override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovie(filmId)

            override fun saveCallResult(data: MovieResponse) {
                localDataSource.updateFilm(FilmEntity(data.id, data.title, data.genre, data.overview, data.imdbScore, data.releaseYear, data.duration, data.photo, data.favorited, data.type))
            }

        }.asLiveData()
    }

    override fun getTvShow(filmId: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, TvShowResponse>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getFilm(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data?.overview ==""

            public override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShow(filmId)

            override fun saveCallResult(data: TvShowResponse) {
                localDataSource.updateFilm(FilmEntity(data.id, data.title, data.genre, data.overview, data.imdbScore, data.releaseYear, data.duration, data.photo, data.favorited, data.type))
            }

        }.asLiveData()
    }

    override fun getFavoritedFilm(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedFilm(), config).build()
    }

    override fun getFavoritedTvShow(): LiveData<PagedList<FilmEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvShow(), config).build()
    }

    override fun setFavoriteFilm(film: FilmEntity, state: Boolean) {
        film.favorited = state;
    }


    override fun setFavoriteTvShow(tv: FilmEntity, state: Boolean) {
        tv.favorited = state;
    }
}