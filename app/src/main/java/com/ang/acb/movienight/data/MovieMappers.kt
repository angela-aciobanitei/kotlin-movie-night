package com.ang.acb.movienight.data

import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.MovieFilter

fun MovieFilter.asStringResourceId() = when (this) {
    MovieFilter.POPULAR -> R.string.filter_by_popular
    MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
    MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
    MovieFilter.UPCOMING -> R.string.filter_by_upcoming
}