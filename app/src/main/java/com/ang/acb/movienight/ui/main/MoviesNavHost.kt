package com.ang.acb.movienight.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ang.acb.movienight.ui.favorites.FavoriteMoviesScreen
import com.ang.acb.movienight.ui.filter.MoviesScreen
import com.ang.acb.movienight.ui.search.SearchMoviesScreen
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun MoviesNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.DISCOVER
    ) {
        composable(route = Routes.DISCOVER) {
            MoviesScreen()
        }
        composable(route = Routes.SEARCH) {
            SearchMoviesScreen()
        }
        composable(route = Routes.FAVORITES) {
            FavoriteMoviesScreen()
        }
    }
}