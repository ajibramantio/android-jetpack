package com.dicoding.movielist.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "filmentities")
data class FilmEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "filmId")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "imdbScore")
    var imdbScore: Int,

    @ColumnInfo(name = "releaseYear")
    var releaseYear: Int,

    @ColumnInfo(name = "duration")
    var duration: Int,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String
) {
    @Embedded
    var filmEntity: FilmEntity? = null
}