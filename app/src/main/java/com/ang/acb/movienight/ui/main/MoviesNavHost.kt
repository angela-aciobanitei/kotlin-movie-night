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
import com.ang.acb.movienight.ui.details.CastDetailsScreen
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
            navController.navigate(LeafScreen.ShowMovieDetails.createRoute(movieId))
        }

        val openCastDetails: (movieId: Long, castId: Long) -> Unit = { movieId, castId ->
            navController.navigate(LeafScreen.ShowCastDetails.createRoute(movieId, castId))
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
            route = LeafScreen.ShowMovieDetails.route,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType }),
            content = { backStackEntry ->
                MovieDetailsScreen(
                    openCastDetails = openCastDetails,
                    upPressed = { navController.navigateUp() }
                )
            }
        )

        composable(
            route = LeafScreen.ShowCastDetails.route,
            arguments = listOf(
                navArgument("movieId") { type = NavType.LongType },
                navArgument("castId") { type = NavType.LongType },
            ),
            content = { backStackEntry ->
                CastDetailsScreen(
                    upPressed = { navController.navigateUp() }
                )
            }
        )
    }
}