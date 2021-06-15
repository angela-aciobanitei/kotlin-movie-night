package com.ang.acb.movienight.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "TODO Favorites")
            }
        }
    }
}