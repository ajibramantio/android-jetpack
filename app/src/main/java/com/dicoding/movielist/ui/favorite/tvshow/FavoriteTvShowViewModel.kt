package com.dicoding.movielist.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class FavoriteTvShowViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getFavoritedTvShow(): LiveData<PagedList<FilmEntity>> = filmRepository.getFavoritedTvShow()

    fun setFavorite(filmEntity: FilmEntity) {
        val newState = !filmEntity.favorited
        filmRepository.setFavoriteTvShow(filmEntity, newState)
    }

}