package com.dicoding.movielist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.dicoding.movielist.data.source.local.LocalDataSource
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.ApiResponse
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse
import com.dicoding.movielist.utils.AppExecutors
import com.dicoding.movielist.vo.Resource

class FakeFilmRepository (private val remoteDataSource: RemoteDataSource,
                                         private val localDataSource: LocalDataSource,
                                         private val appExecutors: AppExecutors) :
    FilmDataSource {

    override fun getAllMovies(): LiveData<Resource<PagedList<FilmEntity>>> {
        return object : NetworkBoundResource<PagedList<FilmEntity>, List<FilmEntity>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> =
                localDataSource.getAllMovies()

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
            public override fun loadFromDB(): LiveData<PagedList<FilmEntity>> =
                localDataSource.getAllTvShows()

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
        return object : NetworkBoundResource<FilmEntity, FilmEntity>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getFilm(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data?.overview ==""

            public override fun createCall(): LiveData<ApiResponse<FilmEntity>> =
                remoteDataSource.getMovie(filmId)

            override fun saveCallResult(data: FilmEntity) {
                localDataSource.updateFilm(data)
            }

        }.asLiveData()
    }

    override fun getTvShow(filmId: String): LiveData<Resource<FilmEntity>> {
        return object : NetworkBoundResource<FilmEntity, FilmEntity>(appExecutors) {
            public override fun loadFromDB(): LiveData<FilmEntity> =
                localDataSource.getFilm(filmId)

            override fun shouldFetch(data: FilmEntity?): Boolean =
                data?.overview ==""

            public override fun createCall(): LiveData<ApiResponse<FilmEntity>> =
                remoteDataSource.getTvShow(filmId)

            override fun saveCallResult(data: FilmEntity) {
                localDataSource.updateFilm(data)
            }

        }.asLiveData()
    }

    override fun getFavoritedFilm(): LiveData<List<FilmEntity>> = localDataSource.getFavoritedFilm()
    override fun getFavoritedTvShow(): LiveData<List<FilmEntity>> = localDataSource.getFavoritedTvShow()

    override fun setFavoriteFilm(film: FilmEntity, state: Boolean) = appExecutors.diskIO().execute { localDataSource.setFilmFavorite(film,state) }
    override fun setFavoriteTvShow(tv: FilmEntity, state: Boolean) = appExecutors.diskIO().execute { localDataSource.setFilmFavorite(tv,state) }
}