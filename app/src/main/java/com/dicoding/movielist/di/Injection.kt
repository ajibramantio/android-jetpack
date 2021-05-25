package com.dicoding.movielist.di

import android.content.Context
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.local.LocalDataSource
import com.dicoding.movielist.data.source.local.room.FilmDatabase
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.utils.AppExecutors
import com.dicoding.movielist.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}