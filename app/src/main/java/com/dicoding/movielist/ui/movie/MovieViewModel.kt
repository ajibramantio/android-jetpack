package com.dicoding.movielist.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.vo.Resource

class MovieViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getMovie(): LiveData<Resource<PagedList<FilmEntity>>> = filmRepository.getAllMovies()
}