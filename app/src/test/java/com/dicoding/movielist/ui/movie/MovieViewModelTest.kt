package com.dicoding.movielist.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.entity.FilmEntity
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Suppress("DEPRECATION")
    @Mock
    private lateinit var pagedList: PagedList<FilmEntity>

    @Suppress("DEPRECATION")
    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmEntity>>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(filmRepository)
    }

    @Suppress("DEPRECATION")
    @Test
    fun getMovie() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(10)

        val movies = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        movies.value = dummyMovies
        `when`(filmRepository.getAllMovies()).thenReturn(movies)

        val movieEntities = viewModel.getMovie().value
        verify(filmRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.data?.size)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}