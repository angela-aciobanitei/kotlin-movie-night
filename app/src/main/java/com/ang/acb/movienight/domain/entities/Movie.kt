package com.ang.acb.movienight.domain.entities

import com.ang.acb.movienight.utils.Constants

data class Movie(
    val id: Long,
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val popularity: Double?,
    val voteAverage: Double?,
    val voteCount: Int?
) {
    val posterUrl = if (posterPath != null) Constants.POSTER_URL + posterPath else null
    val backdropUrl = if (backdropPath != null) Constants.BACKDROP_URL + backdropPath else null
}