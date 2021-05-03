package com.dicoding.movielist.data

data class FilmEntity (
    var title: String,
    var genre: String,
    var overview: String,
    var imdbScore: Int,
    var releaseYear: Int,
    var duration:Int,
    var photo:Int
)