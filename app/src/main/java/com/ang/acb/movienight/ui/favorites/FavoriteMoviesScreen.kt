package com.ang.acb.movienight.ui.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ang.acb.movienight.R
import com.ang.acb.movienight.ui.common.LoadingBox
import com.ang.acb.movienight.ui.common.MessageBox
import com.ang.acb.movienight.ui.common.MovieItem

// todo@ang Content padding parameter it is not used
@Composable
internal fun FavoriteMoviesScreen(
    viewModel: FavoritesViewModel = hiltViewModel(),
    openMovieDetails: (movieId: Long) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.95f),
                contentColor = MaterialTheme.colors.onSurface,
                title = { Text(stringResource(R.string.favorites_topbar_label)) },
            )
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
                    val items = viewModel.movies ?: emptyList()
                    itemsIndexed(items) { index, item ->
                        val bottomPadding = if (index == items.size - 1) 64 else 0
                        MovieItem(
                            movie = item,
                            onMovieClick = { movieId -> openMovieDetails(movieId) },
                            modifier = Modifier.padding(bottom = bottomPadding.dp),
                        )
                    }
                }
            }
        }
    }
}
