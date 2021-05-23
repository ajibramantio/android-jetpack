package com.dicoding.movielist.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity

class TvShowViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getTvShow(): LiveData<List<FilmEntity>> = filmRepository.getAllTvShows()
}