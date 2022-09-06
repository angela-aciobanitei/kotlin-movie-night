package com.ang.acb.movienight.ui.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            startDestination = LeafScreen.Discover.createRoute(RootScreen.Discover)
        ) {
            addDiscover(navController, RootScreen.Discover)
            addMovieDetails(navController, RootScreen.Discover)
            addCastDetails(navController, RootScreen.Discover)
        }

        // The "search" nested graph
        navigation(
            route = RootScreen.Search.route,
            startDestination = LeafScreen.Search.createRoute(RootScreen.Search)
        ) {
            addSearch(navController, RootScreen.Search)
            addMovieDetails(navController, RootScreen.Search)
            addCastDetails(navController, RootScreen.Search)
        }

        // The "favorites" nested graph
        navigation(
            route = RootScreen.Favorites.route,
            startDestination = LeafScreen.Favorites.createRoute(RootScreen.Favorites)
        ) {
            addFavorites(navController, RootScreen.Favorites)
            addMovieDetails(navController, RootScreen.Favorites)
            addCastDetails(navController, RootScreen.Favorites)
        }
    }
}

@FlowPreview
private fun NavGraphBuilder.addDiscover(navController: NavController, rootScreen: RootScreen) {
    composable(
        route = LeafScreen.Discover.createRoute(rootScreen)
    ) {
        FilterMoviesScreen(
            openMovieDetails = { movieId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(rootScreen, movieId))
            },
        )
    }
}

@FlowPreview
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
private fun NavGraphBuilder.addSearch(navController: NavController, rootScreen: RootScreen) {
    composable(
        route = LeafScreen.Search.createRoute(rootScreen)
    ) {
        SearchMoviesScreen(
            openMovieDetails = { movieId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(rootScreen, movieId))
            },
        )
    }
}

private fun NavGraphBuilder.addFavorites(navController: NavController, rootScreen: RootScreen) {
    composable(
        route = LeafScreen.Favorites.createRoute(rootScreen)
    ) {
        FavoriteMoviesScreen(
            openMovieDetails = { movieId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(rootScreen, movieId))
            },
        )
    }
}

private fun NavGraphBuilder.addMovieDetails(navController: NavController, rootScreen: RootScreen) {
    composable(
        route = LeafScreen.MovieDetails.createRoute(rootScreen),
        arguments = listOf(navArgument("movieId") { type = NavType.LongType })
    ) {
        MovieDetailsScreen(
            upPressed = {
                navController.popBackStack()
            },
            openCastDetails = { cast ->
                navController.navigate(LeafScreen.CastDetails.createRoute(rootScreen, castId = cast.id, movieId = cast.movieId))
            },
            openSimilarMovieDetails = { movieId ->
                navController.navigate(LeafScreen.MovieDetails.createRoute(rootScreen, movieId = movieId))
            }
        )
    }
}

private fun NavGraphBuilder.addCastDetails(navController: NavController, rootScreen: RootScreen) {
    composable(
        route = LeafScreen.CastDetails.createRoute(rootScreen),
        arguments = listOf(navArgument("castId") { type = NavType.LongType })
    ) {
        CastDetailsScreen(
            upPressed = {
                navController.popBackStack()
            },
        )
    }
}
