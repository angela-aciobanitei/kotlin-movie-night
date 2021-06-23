package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

sealed class RootScreen(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : RootScreen(
        route = "discoverroot",
        labelResId = R.string.bottom_nav_item_label_discover,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : RootScreen(
        route = "searchroot",
        labelResId = R.string.bottom_nav_item_label_search,
        iconResId = R.drawable.ic_search
    )

    object Favorites : RootScreen(
        route = "favoritesroot",
        labelResId = R.string.bottom_nav_item_label_favorites,
        iconResId = R.drawable.ic_favorite
    )
}

sealed class LeafScreen(val route: String) {
    object Discover : LeafScreen("discover")
    object Search : LeafScreen("search")
    object Favorites : LeafScreen("favorites")

    object MovieDetails : LeafScreen("movie/{movieId}") {
        fun createRoute(movieId: Long): String = "movie/$movieId"
    }

    object CastDetails : LeafScreen("cast/{castId}") {
        fun createRoute(castId: Long): String = "cast/$castId"
    }
}