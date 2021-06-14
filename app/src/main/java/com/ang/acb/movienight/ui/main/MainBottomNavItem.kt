package com.ang.acb.movienight.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ang.acb.movienight.R

sealed class MainBottomNavItem(
    val route: String,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
    object Discover : MainBottomNavItem(
        route = "discover",
        labelResId = R.string.discover_route_label,
        iconResId = R.drawable.ic_movie_24
    )

    object Search : MainBottomNavItem(
        route = "search",
        labelResId = R.string.search_route_label,
        iconResId = R.drawable.ic_baseline_search_24
    )

    object Favorites : MainBottomNavItem(
        route = "favorites",
        labelResId = R.string.favorites_route_label,
        iconResId = R.drawable.ic_baseline_favorite_border_24
    )
}