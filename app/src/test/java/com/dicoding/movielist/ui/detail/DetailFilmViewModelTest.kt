package com.dicoding.movielist.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailFilmViewModelTest {

    private lateinit var viewModel: DetailFilmViewModel
    private lateinit var viewModelTv: DetailFilmViewModel
    private val dummyFilm = DataDummy.generateDummyMovie()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val filmTitle = dummyFilm.title
    private val tvShowTitle = dummyTvShow.title

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var filmObserver: Observer<FilmEntity>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)
        viewModel.setSelectedFilm(filmTitle)
        viewModelTv = DetailFilmViewModel(filmRepository)
        viewModelTv.setSelectedFilm(tvShowTitle)
    }

    @Test
    fun getFilm() {
        val film = MutableLiveData<FilmEntity>()
        film.value = dummyFilm

        `when`(filmRepository.getMovie(filmTitle)).thenReturn(film)
        val filmEntity = viewModel.getMovie().value as FilmEntity
        verify(filmRepository).getMovie(filmTitle)
        assertNotNull(filmEntity)
        assertEquals(dummyFilm.title, filmEntity.title)
        assertEquals(dummyFilm.releaseYear, filmEntity.releaseYear)
        assertEquals(dummyFilm.overview, filmEntity.overview)
        assertEquals(dummyFilm.photo, filmEntity.photo)
        assertEquals(dummyFilm.duration, filmEntity.duration)
        assertEquals(dummyFilm.imdbScore, filmEntity.imdbScore)
        assertEquals(dummyFilm.genre, filmEntity.genre)

        viewModel.getMovie().observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyFilm)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<FilmEntity>()
        tvShow.value = dummyTvShow

        `when`(filmRepository.getTvShow(tvShowTitle)).thenReturn(tvShow)
        val tvShowEntity = viewModelTv.getTvShow().value as FilmEntity
        verify(filmRepository).getTvShow(tvShowTitle)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title,tvShowEntity.title)
        assertEquals(dummyTvShow.releaseYear, tvShowEntity.releaseYear)
        assertEquals(dummyTvShow.overview, tvShowEntity.overview)
        assertEquals(dummyTvShow.photo, tvShowEntity.photo)
        assertEquals(dummyTvShow.duration, tvShowEntity.duration)
        assertEquals(dummyTvShow.imdbScore, tvShowEntity.imdbScore)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)

        viewModelTv.getTvShow().observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyTvShow)
    }
}