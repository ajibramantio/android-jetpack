package com.dicoding.movielist.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
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
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var pagedList: PagedList<FilmEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<FilmEntity>>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShows = Resource.success(pagedList)
        Mockito.`when`(dummyTvShows.data?.size).thenReturn(10)

        val tvShows = MutableLiveData<Resource<PagedList<FilmEntity>>>()
        tvShows.value = dummyTvShows
        Mockito.`when`(filmRepository.getAllTvShows()).thenReturn(tvShows)

        val tvShowEntities = viewModel.getTvShow().value
        verify(filmRepository).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(10, tvShowEntities?.data?.size)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}