package com.dicoding.movielist.utils

import android.content.Context
import com.dicoding.movielist.data.source.remote.response.MovieResponse
import com.dicoding.movielist.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import android.util.Log
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<FilmEntity> {
        val list = ArrayList<FilmEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val genre = movie.getString("genre")
                val overview = movie.getString("overview")
                val imdbScore = movie.getInt("imdbScore")
                val releaseYear = movie.getInt("releaseYear")
                val duration = movie.getInt("duration")
                val photo = movie.getString("photo")
                val favorited = movie.getBoolean("favorited")
                val type = movie.getString("type")

                val movieResponse = FilmEntity(id, title, genre, overview, imdbScore, releaseYear, duration, photo, favorited, type)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShows(): List<FilmEntity> {
        val list = ArrayList<FilmEntity>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val id = tvShow.getString("id")
                val title = tvShow.getString("title")
                val genre = tvShow.getString("genre")
                val overview = tvShow.getString("overview")
                val imdbScore = tvShow.getInt("imdbScore")
                val releaseYear = tvShow.getInt("releaseYear")
                val duration = tvShow.getInt("duration")
                val photo = tvShow.getString("photo")
                val favorited = tvShow.getBoolean("favorited")
                val type = tvShow.getString("type")

                val tvShowResponse = FilmEntity(id, title, genre, overview, imdbScore, releaseYear, duration, photo, favorited, type)
                list.add(tvShowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadMovie(filmId: String): MovieResponse {
        var movie: MovieResponse? = null
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movieEntity = listArray.getJSONObject(i)
                if (movieEntity.getString("id") == filmId) {
                    val id = movieEntity.getString("id")
                    val title = movieEntity.getString("title")
                    val genre = movieEntity.getString("genre")
                    val overview = movieEntity.getString("overview")
                    val imdbScore = movieEntity.getInt("imdbScore")
                    val releaseYear = movieEntity.getInt("releaseYear")
                    val duration = movieEntity.getInt("duration")
                    val photo = movieEntity.getString("photo")
                    val favorited = movieEntity.getBoolean("favorited")
                    val type = movieEntity.getString("type")

                    movie = MovieResponse(id, title, genre, overview, imdbScore, releaseYear, duration, photo, favorited, type)
                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movie as MovieResponse
    }

    fun loadTvShow(filmId: String): TvShowResponse {
        var tvShow: TvShowResponse? = null
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponses.json").toString())
            val listArray = responseObject.getJSONArray("tvShows")
            for (i in 0 until listArray.length()) {
                val tvShowEntity = listArray.getJSONObject(i)
                if (tvShowEntity.getString("id") == filmId) {
                    val id = tvShowEntity.getString("id")
                    val title = tvShowEntity.getString("title")
                    val genre = tvShowEntity.getString("genre")
                    val overview = tvShowEntity.getString("overview")
                    val imdbScore = tvShowEntity.getInt("imdbScore")
                    val releaseYear = tvShowEntity.getInt("releaseYear")
                    val duration = tvShowEntity.getInt("duration")
                    val photo = tvShowEntity.getString("photo")
                    val favorited = tvShowEntity.getBoolean("favorited")
                    val type = tvShowEntity.getString("type")

                    tvShow = TvShowResponse(id, title, genre, overview, imdbScore, releaseYear, duration, photo, favorited, type)
                    break
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return tvShow as TvShowResponse
    }
}