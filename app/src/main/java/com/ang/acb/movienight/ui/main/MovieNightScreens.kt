package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

object MoviesRoutes {
    const val DISCOVER = "discover"
    const val SEARCH = "search"
    const val FAVORITES = "favorites"
    const val DETAILS_ROOT = "details"
    const val DETAILS_MOVIE = "details/{movieId}"
    const val DETAILS_CAST = "details/{movieId}/{castId}"
}

sealed class BottomNavScreen(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : BottomNavScreen(
        route = MoviesRoutes.DISCOVER,
        labelResId = R.string.bottom_nav_item_label_discover,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : BottomNavScreen(
        route = MoviesRoutes.SEARCH,
        labelResId = R.string.bottom_nav_item_label_search,
        iconResId = R.drawable.ic_search
    )

    object Favorites : BottomNavScreen(
        route = MoviesRoutes.FAVORITES,
        labelResId = R.string.bottom_nav_item_label_favorites,
        iconResId = R.drawable.ic_favorite
    )
}

sealed class LeafScreen(val route: String) {
    object ShowMovieDetails : LeafScreen(MoviesRoutes.DETAILS_MOVIE) {
        fun createRoute(movieId: Long): String = "${MoviesRoutes.DETAILS_ROOT}/$movieId"
    }

    object ShowCastDetails : LeafScreen(MoviesRoutes.DETAILS_CAST) {
        fun createRoute(movieId: Long, castId: Long): String =
            "${MoviesRoutes.DETAILS_ROOT}/$movieId/$castId"
    }
}
