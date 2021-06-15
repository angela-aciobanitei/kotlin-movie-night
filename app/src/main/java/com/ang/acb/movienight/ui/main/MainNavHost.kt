package com.ang.acb.movienight.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ang.acb.movienight.ui.favorites.FavoriteMoviesScreen
import com.ang.acb.movienight.ui.moviefilter.MoviesScreen
import com.ang.acb.movienight.ui.moviesearch.SearchMoviesScreen
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun MainNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.DISCOVER
    ) {
        composable(route = MainDestinations.DISCOVER) {
            MoviesScreen()
        }
        composable(route = MainDestinations.SEARCH) {
            SearchMoviesScreen()
        }
        composable(route = MainDestinations.FAVORITES) {
            FavoriteMoviesScreen()
        }
    }
}