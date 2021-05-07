package com.dicoding.movielist.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowResponse(
    var title: String,
    var genre: String,
    var overview: String,
    var imdbScore: Int,
    var releaseYear: Int,
    var duration: Int,
    var photo: String
): Parcelable