package com.ang.acb.movienight.ui.main

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.ang.acb.movienight.ui.main.MainBottomNavItem.*
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val bottomNavItems = listOf(Discover, Search, Favorites)

    Scaffold(
        bottomBar = {
            MainBottomNavigation(navController, bottomNavItems)
        },
        content = {
            MainNavHost(navController)
        }
    )
}
