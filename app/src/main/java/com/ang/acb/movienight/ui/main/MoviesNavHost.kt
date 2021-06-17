package com.ang.acb.movienight.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.ang.acb.movienight.ui.details.MovieDetailsScreen
import com.ang.acb.movienight.ui.favorites.FavoriteMoviesScreen
import com.ang.acb.movienight.ui.filter.FilterMoviesScreen
import com.ang.acb.movienight.ui.search.SearchMoviesScreen
import kotlinx.coroutines.FlowPreview

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@FlowPreview
@Composable
fun MoviesNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = BottomNavScreen.Discover.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        val openMovieDetails: (movieId: Long) -> Unit = { movieId ->
            navController.navigate(LeafScreen.ShowDetails.createRoute(movieId))
        }

        composable(route = BottomNavScreen.Discover.route) {
            FilterMoviesScreen(openMovieDetails = openMovieDetails)
        }

        composable(route = BottomNavScreen.Search.route) {
            SearchMoviesScreen(openMovieDetails = openMovieDetails)
        }

        composable(route = BottomNavScreen.Favorites.route) {
            FavoriteMoviesScreen(openMovieDetails = openMovieDetails)
        }

        // See: https://developer.android.com/jetpack/compose/navigation#nav-with-args
        composable(
            route = LeafScreen.ShowDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType }),
            content = { backStackEntry ->
                MovieDetailsScreen(
                    upPressed = { navController.navigateUp() }
                )
            }
        )
    }
}