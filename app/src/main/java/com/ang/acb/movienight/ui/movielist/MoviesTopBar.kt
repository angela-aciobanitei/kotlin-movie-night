package com.ang.acb.movienight.ui.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.MovieFilter

@Composable
fun MoviesTopBar(
    viewModel: MoviesViewModel,
) {
    val titleResId by remember { viewModel.title }
    TopAppBar(
        title = {
            Text(text = stringResource(titleResId))
        },
        actions = {
            MoviesMenu(viewModel)
        }
    )
}

@Composable
fun MoviesMenu(viewModel: MoviesViewModel) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_sort_24),
                contentDescription = null,
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            FilterMenuItem(
                filter = MovieFilter.POPULAR,
                onClick = {
                    viewModel.onFilterChanged(MovieFilter.POPULAR)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.TOP_RATED,
                onClick = {
                    viewModel.onFilterChanged(MovieFilter.TOP_RATED)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.NOW_PLAYING,
                onClick = {
                    viewModel.onFilterChanged(MovieFilter.NOW_PLAYING)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.UPCOMING,
                onClick = {
                    viewModel.onFilterChanged(MovieFilter.UPCOMING)
                    expanded = false
                },
            )
        }
    }
}

@Composable
fun FilterMenuItem(
    filter: MovieFilter,
    onClick: () -> Unit,
) {
    DropdownMenuItem(onClick = onClick) {
        val menuItemTitle = when (filter) {
            MovieFilter.POPULAR -> R.string.filter_by_popular
            MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
            MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
            MovieFilter.UPCOMING -> R.string.filter_by_upcoming
        }
        Text(text = stringResource(id = menuItemTitle))
    }
}