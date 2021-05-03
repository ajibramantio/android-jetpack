package com.dicoding.movielist.ui.tvshow

import com.dicoding.movielist.ui.movie.MovieViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShow() {
        val tvshowEntities = viewModel.getTvShow()
        assertNotNull(tvshowEntities)
        assertEquals(10, tvshowEntities.size)
    }
}