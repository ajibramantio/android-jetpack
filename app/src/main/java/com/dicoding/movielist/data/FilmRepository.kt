package com.dicoding.movielist.data

import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.RemoteDataSource

class FilmRepository private constructor(private val remoteDataSource: RemoteDataSource) : FilmDataSource {

    companion object {
        @Volatile
        private var instance: FilmRepository? = null

        fun getInstance(remoteData: RemoteDataSource): FilmRepository =
            instance ?: synchronized(this) {
                instance ?: FilmRepository(remoteData).apply { instance = this }
            }
    }

    override fun getAllMovies(): List<FilmEntity> {
        val movieResponses = remoteDataSource.getAllMovies()
        val movieList = ArrayList<FilmEntity>()
        for (response in movieResponses) {
            val movie = FilmEntity(response.title,
                response.genre,
                response.overview,
                response.imdbScore,
                response.releaseYear,
                response.duration,
                response.photo)
            movieList.add(movie)
        }
        return movieList
    }

    override fun getAllTvShows(): List<FilmEntity> {
        val tvShowResponses = remoteDataSource.getAllTvShows()
        val tvShowList = ArrayList<FilmEntity>()
        for (response in tvShowResponses) {
            val tvShow = FilmEntity(response.title,
                response.genre,
                response.overview,
                response.imdbScore,
                response.releaseYear,
                response.duration,
                response.photo)
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    override fun getMovie(filmTitle: String): FilmEntity {
        val movieResponse = remoteDataSource.getAllMovies()
        lateinit var movie: FilmEntity
        for(response in movieResponse) {
            if (response.title == filmTitle) {
                movie = FilmEntity(response.title,
                    response.genre,
                    response.overview,
                    response.imdbScore,
                    response.releaseYear,
                    response.duration,
                    response.photo)
                movie.filmEntity = FilmEntity(remoteDataSource.getMovie(filmTitle).title, remoteDataSource.getMovie(filmTitle).genre, remoteDataSource.getMovie(filmTitle).overview, remoteDataSource.getMovie(filmTitle).imdbScore, remoteDataSource.getMovie(filmTitle).releaseYear, remoteDataSource.getMovie(filmTitle).duration, remoteDataSource.getMovie(filmTitle).photo)
                break
            }
        }
        return movie
    }

    override fun getTvShow(filmTitle: String): FilmEntity {
        val tvShowResponse = remoteDataSource.getAllTvShows()
        lateinit var tvShow: FilmEntity
        for(response in tvShowResponse) {
            if (response.title == filmTitle) {
                tvShow = FilmEntity(response.title,
                    response.genre,
                    response.overview,
                    response.imdbScore,
                    response.releaseYear,
                    response.duration,
                    response.photo)
                tvShow.filmEntity = FilmEntity(remoteDataSource.getTvShow(filmTitle).title, remoteDataSource.getTvShow(filmTitle).genre, remoteDataSource.getTvShow(filmTitle).overview, remoteDataSource.getTvShow(filmTitle).imdbScore, remoteDataSource.getTvShow(filmTitle).releaseYear, remoteDataSource.getTvShow(filmTitle).duration, remoteDataSource.getTvShow(filmTitle).photo)
                break
            }
        }
        return tvShow
    }
}