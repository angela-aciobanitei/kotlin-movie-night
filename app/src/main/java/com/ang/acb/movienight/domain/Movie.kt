package com.ang.acb.movienight.domain

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
)