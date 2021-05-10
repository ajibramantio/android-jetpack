package com.dicoding.movielist.ui.movie

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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<List<FilmEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(filmRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovies = DataDummy.generateDummyMovie()
        val movies = MutableLiveData<List<FilmEntity>>()
        movies.value = dummyMovies

        `when`(filmRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMovie().value
        verify(filmRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}