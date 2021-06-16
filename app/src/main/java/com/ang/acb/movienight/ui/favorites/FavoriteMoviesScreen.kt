package com.ang.acb.movienight.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ang.acb.movienight.ui.common.MovieItem

@Composable
fun FavoriteMoviesScreen(
    viewModel: FavoritesViewModel,
    navController: NavHostController,
) {
    Scaffold(
        topBar = { FavoritesTopBar() }
    ) {
        LazyColumn {
            items(viewModel.movies) { item ->
                MovieItem(
                    movie = item,
                    onMovieClick = { movieId ->
                        navController.navigate("movies/details/$movieId")
                    }
                )
            }
        }
    }
}