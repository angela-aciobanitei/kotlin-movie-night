package com.ang.acb.movienight.data.source.local

import com.ang.acb.movienight.domain.entities.Movie

fun FavoriteMovie.asMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    backdropPath = backdropPath,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
    isFavorite = isFavorite,
)

fun List<FavoriteMovie>.asMovies() = this.map { it.asMovie() }