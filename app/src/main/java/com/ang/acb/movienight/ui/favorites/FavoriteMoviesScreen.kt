package com.ang.acb.movienight.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import com.ang.acb.movienight.ui.common.MovieItem

@Composable
internal fun FavoriteMoviesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.favorites_topbar_label)) })
        }
    ) {
        if (viewModel.isLoading) {
            LoadingBox()
        } else {
            if (viewModel.errorMessage != null) {
                MessageBox(messageResId = viewModel.errorMessage!!)
            } else if (viewModel.movies?.isEmpty() == true) {
                MessageBox(messageResId = R.string.no_favorites_hint_message)
            } else {
                LazyColumn {
                    items(viewModel.movies ?: emptyList()) { item ->
                        MovieItem(
                            movie = item,
                            onMovieClick = { movieId -> openMovieDetails(movieId) }
                        )
                    }
                }
            }
        }
    }
}
