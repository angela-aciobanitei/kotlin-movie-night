package com.ang.acb.movienight.ui.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        MainBottomNavItem.Discover,
        MainBottomNavItem.Search,
        MainBottomNavItem.Favorites,
    )
    Scaffold(
        bottomBar = { MainBottomNavigation(navController, bottomNavigationItems) },
    ) {
        MainNavHost(navController)
    }
}
