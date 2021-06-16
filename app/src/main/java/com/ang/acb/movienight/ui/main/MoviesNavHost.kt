package com.ang.acb.movienight.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ang.acb.movienight.ui.details.MovieDetailsScreen
import com.ang.acb.movienight.ui.favorites.FavoriteMoviesScreen
import com.ang.acb.movienight.ui.filter.FilterMoviesScreen
import com.ang.acb.movienight.ui.search.SearchMoviesScreen
import kotlinx.coroutines.FlowPreview

object MoviesRoutes {
    const val MOVIES_ROOT = "movies"
    const val DISCOVER = "movies/discover"
    const val SEARCH = "movies/search"
    const val FAVORITES = "movies/favorites"
    const val DETAILS_ROOT = "details"
    const val DETAILS = "movies/details/{movieId}"
}

@FlowPreview
@ExperimentalComposeUiApi
@Composable
fun MoviesNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = MoviesRoutes.MOVIES_ROOT,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        // Nested graph for bottom bar navigation
        navigation(
            route = MoviesRoutes.MOVIES_ROOT,
            startDestination = MoviesRoutes.DISCOVER,
            builder = {
                addBottomBarTabs(navController = navController)
            },
        )

        // See: https://developer.android.com/jetpack/compose/navigation#nav-with-args
        composable(
            route = MoviesRoutes.DETAILS,
            arguments = listOf(navArgument("movieId") { type = NavType.LongType }),
            content = { backStackEntry ->
                MovieDetailsScreen(
                    navController = navController,
                    movieId = backStackEntry.arguments?.getLong("movieId")!!
                )
            }
        )
    }
}

@FlowPreview
@ExperimentalComposeUiApi
private fun NavGraphBuilder.addBottomBarTabs(
    navController: NavHostController,
) {
    composable(route = MoviesRoutes.DISCOVER) {
        FilterMoviesScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }

    composable(route = MoviesRoutes.SEARCH) {
        SearchMoviesScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }

    composable(route = MoviesRoutes.FAVORITES) {
        FavoriteMoviesScreen(
            viewModel = hiltViewModel(),
            navController = navController
        )
    }
}