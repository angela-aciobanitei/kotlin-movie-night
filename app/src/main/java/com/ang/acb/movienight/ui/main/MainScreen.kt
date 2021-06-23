package com.ang.acb.movienight.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.FlowPreview

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val bottomNavItems = listOf(RootScreen.Discover, RootScreen.Search, RootScreen.Favorites)
    val bottomNavRoutes = listOf(LeafScreen.Discover, LeafScreen.Search, LeafScreen.Favorites)
        .map { it.route }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomNavRoutes // todo find a better way to determine this

    Scaffold(
        bottomBar = {
            if (showBottomBar) MoviesBottomBar(navController, bottomNavItems)
        },
        content = {
            MoviesNavHost(navController)
        }
    )
}
