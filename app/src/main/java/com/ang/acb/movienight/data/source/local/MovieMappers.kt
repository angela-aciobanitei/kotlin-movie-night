package com.ang.acb.movienight.data.source.local

import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.entities.Movie
import com.ang.acb.movienight.domain.entities.MovieFilter

fun MovieFilter.asStringResId() = when (this) {
    MovieFilter.POPULAR -> R.string.filter_by_popular
    MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
    MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
    MovieFilter.UPCOMING -> R.string.filter_by_upcoming
}

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