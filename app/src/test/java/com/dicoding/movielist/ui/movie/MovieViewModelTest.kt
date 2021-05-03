package com.dicoding.movielist.ui.movie

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel()
    }

    @Test
    fun getMovie() {
        val filmEntities = viewModel.getMovie()
        assertNotNull(filmEntities)
        assertEquals(10, filmEntities.size)
    }
}