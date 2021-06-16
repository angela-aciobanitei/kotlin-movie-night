package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

sealed class MoviesBottomNavItem(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : MoviesBottomNavItem(
        route = Routes.DISCOVER,
        labelResId = R.string.discover_route_label,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : MoviesBottomNavItem(
        route = Routes.SEARCH,
        labelResId = R.string.search_route_label,
        iconResId = R.drawable.ic_search
    )

    object Favorites : MoviesBottomNavItem(
        route = Routes.FAVORITES,
        labelResId = R.string.favorites_route_label,
        iconResId = R.drawable.ic_favorite
    )
}