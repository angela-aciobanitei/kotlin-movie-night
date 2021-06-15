package com.ang.acb.movienight.data

import com.ang.acb.movienight.R
import com.ang.acb.movienight.data.local.FavoriteMovie
import com.ang.acb.movienight.data.remote.MoviesResponse
import com.ang.acb.movienight.data.remote.NetworkMovie
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import com.ang.acb.movienight.domain.Movies

fun MoviesResponse.asMovies() = Movies(
    movies = results.map { it.asMovie() },
    currentPage = page,
    totalPages = totalPages
)

fun NetworkMovie.asMovie() = Movie(
    id = id,
    title = title,
    overview = overview,
    releaseDate = releaseDate,
    posterPath = posterPath,
    backdropPath = backdropPath,
    popularity = popularity,
    voteAverage = voteAverage,
    voteCount = voteCount,
)

fun MovieFilter.asStringResourceId() = when (this) {
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
)

fun List<FavoriteMovie>.asMovies() = this.map { it.asMovie() }