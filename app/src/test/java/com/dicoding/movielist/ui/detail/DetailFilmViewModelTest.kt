package com.dicoding.movielist.ui.detail

import com.dicoding.movielist.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailFilmViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private val dummyFilm = DataDummy.generateDummyMovie()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]


    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel()
    }

    @Test
    fun getFilm() {
        viewModel.setSelectedFilm(dummyFilm.title)
        val filmEntity = viewModel.getFilm()
        assertNotNull(filmEntity)
        assertEquals(dummyFilm.title, filmEntity.title)
        assertEquals(dummyFilm.releaseYear, filmEntity.releaseYear)
        assertEquals(dummyFilm.overview, filmEntity.overview)
        assertEquals(dummyFilm.photo, filmEntity.photo)
        assertEquals(dummyFilm.duration, filmEntity.duration)
        assertEquals(dummyFilm.imdbScore, filmEntity.imdbScore)
        assertEquals(dummyFilm.genre, filmEntity.genre)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedFilm(dummyTvShow.title)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title,tvShowEntity.title)
        assertEquals(dummyTvShow.releaseYear, tvShowEntity.releaseYear)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.photo, tvShowEntity.photo)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.imdbScore, tvShowEntity.imdbScore)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
    }
}