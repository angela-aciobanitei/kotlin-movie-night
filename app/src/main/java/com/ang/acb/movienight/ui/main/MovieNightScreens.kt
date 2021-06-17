package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

object MoviesRoutes {
    const val DISCOVER = "discover"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
    const val DETAILS_ROOT = "details"
    const val DETAILS = "details/{movieId}"
}

sealed class BottomNavScreen(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : BottomNavScreen(
        route = MoviesRoutes.DISCOVER,
        labelResId = R.string.discover_route_label,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : BottomNavScreen(
        route = MoviesRoutes.SEARCH,
        labelResId = R.string.search_route_label,
        iconResId = R.drawable.ic_search
    )

    object Favorites : BottomNavScreen(
        route = MoviesRoutes.FAVORITES,
        labelResId = R.string.favorites_route_label,
        iconResId = R.drawable.ic_favorite
    )
}

sealed class LeafScreen(val route: String) {
    object MovieDetails : LeafScreen(MoviesRoutes.DETAILS) {
        fun createRoute(movieId: Long): String = "${MoviesRoutes.DETAILS_ROOT}/$movieId"
    }
}
