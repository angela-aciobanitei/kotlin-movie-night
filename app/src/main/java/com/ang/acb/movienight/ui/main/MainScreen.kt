package com.ang.acb.movienight.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ang.acb.movienight.ui.main.BottomNavScreen.*
import kotlinx.coroutines.FlowPreview

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val bottomNavItems = listOf(Discover, Search, Favorites)
    val bottomNavRoutes = bottomNavItems.map { it.route }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomNavRoutes) MoviesBottomBar(navController, bottomNavItems)
        },
        content = {
            MoviesNavHost(navController)
        }
    )
}
