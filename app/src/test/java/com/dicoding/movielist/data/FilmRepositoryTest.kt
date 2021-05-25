package com.dicoding.movielist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.movielist.data.source.local.LocalDataSource
import com.dicoding.movielist.data.source.local.entity.FilmEntity
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.util.LiveDataTestUtil
import com.dicoding.movielist.util.PagedListUtil
import com.dicoding.movielist.utils.AppExecutors
import com.dicoding.movielist.utils.DataDummy
import com.dicoding.movielist.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doNothing
import org.junit.Rule
import org.mockito.Mockito

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val filmId = movieResponses[0].id

    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvId = tvShowResponses[0].id

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        filmRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FilmEntity>
        Mockito.`when`(local.getAllTvShows()).thenReturn(dataSourceFactory)
        filmRepository.getAllTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        val dummyMovie =  DataDummy.generateDummyMovie()[0]
        dummyEntity.value = dummyMovie
        Mockito.`when`<LiveData<FilmEntity>>(local.getFilm(filmId)).thenReturn(dummyEntity)

        val resultMovie = LiveDataTestUtil.getValue(filmRepository.getMovie(filmId))
        verify(local).getFilm(eq(filmId))
        assertNotNull(resultMovie)
        assertNotNull(resultMovie.data?.title)
        assertEquals(dummyMovie.title, resultMovie.data?.title)
    }

    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<FilmEntity>()
        val dummyTvShow =  DataDummy.generateDummyTvShows()[0]
        dummyEntity.value = dummyTvShow
        Mockito.`when`<LiveData<FilmEntity>>(local.getFilm(tvId)).thenReturn(dummyEntity)

        val resultTvShow = LiveDataTestUtil.getValue(filmRepository.getTvShow(tvId))
        verify(local).getFilm(eq(tvId))
        assertNotNull(resultTvShow)
        assertNotNull(resultTvShow.data?.title)
        assertEquals(dummyTvShow.title, resultTvShow.data?.title)
    }

    @Test
    fun setFavoriteUnFavoriteMovie() {
        val dummyMovie = DataDummy.generateDummyMovie()[0]
        doNothing().`when`(local).setFilmFavorite(dummyMovie, !dummyMovie.favorited)
        filmRepository.setFavoriteFilm(dummyMovie, !dummyMovie.favorited)
        local.setFilmFavorite(dummyMovie, !dummyMovie.favorited)
        verify(local).setFilmFavorite(dummyMovie, !dummyMovie.favorited)
        //set favorite
        assertEquals(true, dummyMovie.favorited)

        filmRepository.setFavoriteFilm(dummyMovie, !dummyMovie.favorited)
        local.setFilmFavorite(dummyMovie, !dummyMovie.favorited)
        //set unfavorite
        assertEquals(false, dummyMovie.favorited)

    }

    @Test
    fun setFavoriteUnFavoriteTvShow() {
        val dummyTvShow = DataDummy.generateDummyTvShows()[1]
        doNothing().`when`(local).setFilmFavorite(dummyTvShow,!dummyTvShow.favorited)
        filmRepository.setFavoriteTvShow(dummyTvShow,!dummyTvShow.favorited)
        local.setFilmFavorite(dummyTvShow,!dummyTvShow.favorited)
        verify(local).setFilmFavorite(dummyTvShow,!dummyTvShow.favorited)
        //set favorite
        assertEquals(true, dummyTvShow.favorited)

        filmRepository.setFavoriteTvShow(dummyTvShow,!dummyTvShow.favorited)
        local.setFilmFavorite(dummyTvShow,!dummyTvShow.favorited)
        //set unfavorite
        assertEquals(false, dummyTvShow.favorited)
    }
}