package com.ang.acb.movienight.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MovieFilter
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ang.acb.movienight.R

sealed class RootScreen(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
) {
    object Discover : RootScreen(
        route = "discoverroot",
        label = R.string.bottom_nav_item_label_discover,
        icon = Icons.Default.MovieFilter,
    )

    object Search : RootScreen(
        route = "searchroot",
        label = R.string.bottom_nav_item_label_search,
        icon = Icons.Default.Search,
    )

    object Favorites : RootScreen(
        route = "favoritesroot",
        label = R.string.bottom_nav_item_label_favorites,
        icon = Icons.Default.Favorite,
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