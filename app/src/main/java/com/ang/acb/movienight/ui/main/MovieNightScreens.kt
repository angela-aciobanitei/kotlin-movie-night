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
        route = "discover",
        label = R.string.bottom_nav_item_label_discover,
        icon = Icons.Default.MovieFilter,
    )

    object Search : RootScreen(
        route = "search",
        label = R.string.bottom_nav_item_label_search,
        icon = Icons.Default.Search,
    )

    object Favorites : RootScreen(
        route = "favorites",
        label = R.string.bottom_nav_item_label_favorites,
        icon = Icons.Default.Favorite,
    )
}

sealed class LeafScreen(val route: String) {

    object Discover : LeafScreen("discover") {
        fun createRoute(root: RootScreen) = "${root.route}/$route"
    }

    object Search : LeafScreen("search") {
        fun createRoute(root: RootScreen) = "${root.route}/$route"
    }

    object Favorites : LeafScreen("favorites") {
        fun createRoute(root: RootScreen) = "${root.route}/$route"
    }

    object MovieDetails : LeafScreen("movie/{movieId}") {
        fun createRoute(root: RootScreen) = "${root.route}/$route"

        fun createRoute(rootScreen: RootScreen, movieId: Long): String {
            return "${rootScreen.route}/movie/$movieId"
        }
    }

    object CastDetails : LeafScreen("movie/{movieId}/cast/{castId}") {
        fun createRoute(root: RootScreen) = "${root.route}/$route"

        fun createRoute(rootScreen: RootScreen, movieId: Long, castId: Long): String {
            return "${rootScreen.route}/movie/$movieId/cast/$castId"
        }
    }
}