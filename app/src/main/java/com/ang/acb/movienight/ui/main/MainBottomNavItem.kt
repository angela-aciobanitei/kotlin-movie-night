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
        route = MainDestinations.DISCOVER,
        labelResId = R.string.discover_route_label,
        iconResId = R.drawable.ic_discover_movies
    )

    object Search : MainBottomNavItem(
        route = MainDestinations.SEARCH,
        labelResId = R.string.search_route_label,
        iconResId = R.drawable.ic_search
    )

    object Favorites : MainBottomNavItem(
        route = MainDestinations.FAVORITES,
        labelResId = R.string.favorites_route_label,
        iconResId = R.drawable.ic_favorite
    )
}