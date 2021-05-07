package com.dicoding.movielist.di

import android.content.Context
import com.dicoding.movielist.data.FilmRepository
import com.dicoding.movielist.data.source.remote.RemoteDataSource
import com.dicoding.movielist.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): FilmRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return FilmRepository.getInstance(remoteDataSource)
    }
}