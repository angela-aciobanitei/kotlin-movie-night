package com.ang.acb.movienight.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
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
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Discover.route,
    ) {
        // The "discover" nested graph
        navigation(
            route = RootScreen.Discover.route,
            startDestination = LeafScreen.Discover.route
        ) {
            addDiscover(navController)
            addShowDetails(navController)
            addCastDetails(navController)
        }

        // The "search" nested graph
        navigation(
            route = RootScreen.Search.route,
            startDestination = LeafScreen.Search.route
        ) {
            addSearch(navController)
            addShowDetails(navController)
            addCastDetails(navController)
        }

        // The "favorites" nested graph
        navigation(
            route = RootScreen.Favorites.route,
            startDestination = LeafScreen.Favorites.route
        ) {
            addFavorites(navController)
            addShowDetails(navController)
            addCastDetails(navController)
        }
    }
}

@FlowPreview
private fun NavGraphBuilder.addDiscover(navController: NavController) {
    composable(LeafScreen.Discover.route) {
        FilterMoviesScreen(
            openMovieDetails = { showId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(showId))
            },
        )
    }
}

@FlowPreview
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
private fun NavGraphBuilder.addSearch(navController: NavController) {
    composable(LeafScreen.Search.route) {
        SearchMoviesScreen(
            openMovieDetails = { showId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(showId))
            },
        )
    }
}

private fun NavGraphBuilder.addFavorites(navController: NavController) {
    composable(LeafScreen.Favorites.route) {
        FavoriteMoviesScreen(
            openMovieDetails = { showId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(showId))
            },
        )
    }
}

private fun NavGraphBuilder.addShowDetails(navController: NavController) {
    composable(
        route = LeafScreen.MovieDetails.route,
        arguments = listOf(
            navArgument("movieId") { type = NavType.LongType }
        )
    ) {
        MovieDetailsScreen(
            upPressed = {
                navController.popBackStack()
            },
            openCastDetails = { castId ->
                navController.navigate(LeafScreen.CastDetails.createRoute(castId))
            },
            openSimilarMovieDetails = { movieId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(movieId))
            }
        )
    }
}

private fun NavGraphBuilder.addCastDetails(navController: NavController) {
    composable(
        route = LeafScreen.CastDetails.route,
        arguments = listOf(
            navArgument("castId") { type = NavType.LongType }
        )
    ) {
        CastDetailsScreen(
            upPressed = {
                navController.popBackStack()
            },
        )
    }
}
