package com.ang.acb.movienight.data

import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.Movie
import com.ang.acb.movienight.domain.MovieFilter
import com.ang.acb.movienight.domain.Movies

fun MoviesResponse.asMovies(): Movies {
    return Movies(
        movies = results.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
            )
        },
        currentPage = page,
        totalPages = totalPages
    )
}

fun MovieFilter.asStringResourceId() = when (this) {
    MovieFilter.POPULAR -> R.string.filter_by_popular
    MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
    MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
    MovieFilter.UPCOMING -> R.string.filter_by_upcoming
}