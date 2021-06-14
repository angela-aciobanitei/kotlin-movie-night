package com.ang.acb.movienight.ui.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ang.acb.movienight.ui.filtermovies.MoviesScreen
import com.ang.acb.movienight.ui.searchmovies.SearchMoviesScreen
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
        composable(MainDestinations.DISCOVER) {
            MoviesScreen()
        }
        composable(MainDestinations.SEARCH) {
            SearchMoviesScreen()
        }
        composable(MainDestinations.FAVORITES) {
            Text(text = "Favorites")
        }
    }
}