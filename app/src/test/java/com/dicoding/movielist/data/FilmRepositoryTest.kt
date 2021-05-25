package com.dicoding.movielist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.movielist.data.source.local.LocalDataSource
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.utils.AppExecutors
import com.dicoding.movielist.utils.DataDummy
import com.dicoding.movielist.utils.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Rule

class FilmRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val filmRepository = FakeFilmRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val filmTitle = movieResponses[0].title
    private val detailFilm = movieResponses[0]

    private val tvShowResponses = DataDummy.generateRemoteDummyTvShows()
    private val tvTitle = tvShowResponses[0].title
    private val detailTv = tvShowResponses[0]

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies()

        val movieEntities = LiveDataTestUtil.getValue(filmRepository.getAllMovies())
        verify(remote).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(tvShowResponses)
            null
        }.`when`(remote).getAllTvShows()

        val tvShowEntities = LiveDataTestUtil.getValue(filmRepository.getAllTvShows())
        verify(remote).getAllTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback).onMovieReceived(detailFilm)
            null
        }.`when`(remote).getMovie(eq(filmTitle))

        val resultMovie = LiveDataTestUtil.getValue(filmRepository.getMovie(filmTitle))
        verify(remote)
            .getMovie(eq(filmTitle))
        assertNotNull(resultMovie)
        assertNotNull(resultMovie.data?.title)
        assertEquals(detailFilm.title, resultMovie.data?.title)
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback).onTvShowReceived(detailTv)
            null
        }.`when`(remote).getTvShow(eq(tvTitle))

        val resultTvShow = LiveDataTestUtil.getValue(filmRepository.getTvShow(tvTitle))
        verify(remote).getTvShow(eq(tvTitle))
        assertNotNull(resultTvShow)
        assertNotNull(resultTvShow.data?.title)
        assertEquals(detailTv.title, resultTvShow.data?.title)
    }
}