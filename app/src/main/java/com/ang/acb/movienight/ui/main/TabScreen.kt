package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

sealed class TabScreen(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : TabScreen(
        route = MoviesRoutes.DISCOVER,
        labelResId = R.string.discover_route_label,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : TabScreen(
        route = MoviesRoutes.SEARCH,
        labelResId = R.string.search_route_label,
        iconResId = R.drawable.ic_search
    )

    object Favorites : TabScreen(
        route = MoviesRoutes.FAVORITES,
        labelResId = R.string.favorites_route_label,
        iconResId = R.drawable.ic_favorite
    )
}

sealed class LeafScreen(val route: String) {
    object MovieDetails : LeafScreen(MoviesRoutes.DETAILS) {
        fun createRoute(movieId: Long): String = "details/$movieId"
    }
}
