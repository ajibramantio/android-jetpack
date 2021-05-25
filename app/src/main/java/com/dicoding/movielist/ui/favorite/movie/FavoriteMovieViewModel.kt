package com.dicoding.movielist.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class FavoriteMovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getFavoritedMovie(): LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedFilm()

    fun setFavorite(filmEntity: FilmEntity) {
        val newState = !filmEntity.favorited
        filmRepository.setFavoriteFilm(filmEntity, newState)
    }

}