package com.dicoding.movielist.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.vo.Resource

class DetailFilmViewModel(private val filmRepository: FilmRepository): ViewModel() {

    val filmId = MutableLiveData<String>()

    fun setSelectedFilm(filmId: String) {
        this.filmId.value = filmId
    }

    var getMovie: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getMovie(mFilmId)
    }
    var getTvShow: LiveData<Resource<FilmEntity>> = Transformations.switchMap(filmId) { mFilmId ->
        filmRepository.getTvShow(mFilmId)
    }

    fun setFavoriteMovie() {
        val filmResource = getMovie.value

        if (filmResource != null) {
            val film =filmResource.data
            if (film != null) {
                val newState = !film.favorited
                filmRepository.setFavoriteFilm(film, newState)
            }
        }
    }

    fun setFavoriteTv(){
        val filmResource = getTvShow.value
        if (filmResource != null) {
            val film =filmResource.data
            if (film != null) {
                val newState = !film.favorited
                filmRepository.setFavoriteFilm(film, newState)
            }
        }

    }
}