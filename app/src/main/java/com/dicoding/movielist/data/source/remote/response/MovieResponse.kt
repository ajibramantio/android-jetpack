package com.dicoding.movielist.data.source.remote.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    var id: String,
    var title: String,
    var genre: String,
    var overview: String,
    var imdbScore: Int,
    var releaseYear: Int,
    var duration: Int,
    var photo: String,
    var favorited: Boolean = false,
    var type: String
): Parcelable