package com.ang.acb.movienight.data

import com.ang.acb.movienight.domain.Movie
import com.google.gson.annotations.SerializedName

data class NetworkMovie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String?,
)

fun List<NetworkMovie>.asMovies(): List<Movie> {
    return this.map {
        Movie(
            id = it.id,
            title = it.title,
        )
    }
}