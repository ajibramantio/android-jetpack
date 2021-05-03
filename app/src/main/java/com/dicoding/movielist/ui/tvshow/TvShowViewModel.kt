package com.dicoding.movielist.ui.tvshow

import androidx.lifecycle.ViewModel
import com.dicoding.movielist.data.FilmEntity
import com.dicoding.movielist.utils.DataDummy

class TvShowViewModel: ViewModel() {
    fun getTvShow(): List<FilmEntity> = DataDummy.generateDummyTvShows()
}