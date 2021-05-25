package com.dicoding.movielist.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.vo.Resource

class TvShowViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getTvShow(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getAllTvShows()
}