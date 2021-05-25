package com.dicoding.movielist.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.utils.DataDummy
import com.dicoding.movielist.vo.Resource
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
    private val filmId = dummyFilm.id
    private val tvShowId = dummyTvShow.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var filmObserver: Observer<Resource<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = DetailFilmViewModel(filmRepository)
        viewModel.setSelectedFilm(filmId)

        viewModelTv = DetailFilmViewModel(filmRepository)
        viewModelTv.setSelectedFilm(tvShowId)
    }

    @Test
    fun getFilm() {
        val dummyMovie = Resource.success(dummyFilm)
        val film = MutableLiveData<Resource<FilmEntity>>()
        film.value = dummyMovie

        `when`(filmRepository.getMovie(filmId)).thenReturn(film)
        viewModel.getMovie.observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvShow() {
        val dummyTv = Resource.success(dummyFilm)
        val tvShow = MutableLiveData<Resource<FilmEntity>>()
        tvShow.value = dummyTv

        `when`(filmRepository.getTvShow(tvShowId)).thenReturn(tvShow)
        viewModelTv.getTvShow.observeForever(filmObserver)
        verify(filmObserver).onChanged(dummyTv)
    }
}