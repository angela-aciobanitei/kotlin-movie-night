package com.ang.acb.movienight.ui.movielist

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.R
import com.ang.acb.movienight.domain.MovieFilter

@Composable
fun MoviesTopBar(
    viewModel: MoviesViewModel,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(
                    when (viewModel.movieFilter) {
                        MovieFilter.POPULAR -> R.string.filter_by_popular
                        MovieFilter.TOP_RATED -> R.string.filter_by_top_rated
                        MovieFilter.NOW_PLAYING -> R.string.filter_by_now_playing
                        MovieFilter.UPCOMING -> R.string.filter_by_upcoming
                    }
                )
            )
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
            MenuItem(
                onClick = {
                    viewModel.movieFilter = MovieFilter.POPULAR
                    expanded = false
                },
                titleResId = R.string.filter_by_popular,
            )

            MenuItem(
                onClick = {
                    viewModel.movieFilter = MovieFilter.TOP_RATED
                    expanded = false
                },
                titleResId = R.string.filter_by_top_rated,
            )

            MenuItem(
                onClick = {
                    viewModel.movieFilter = MovieFilter.NOW_PLAYING
                    expanded = false
                },
                titleResId = R.string.filter_by_now_playing,
            )

            MenuItem(
                onClick = {
                    viewModel.movieFilter = MovieFilter.UPCOMING
                    expanded = false
                },
                titleResId = R.string.filter_by_upcoming,
            )
        }
    }
}

@Composable
fun MenuItem(
    onClick: () -> Unit,
    @StringRes titleResId: Int,
) {
    DropdownMenuItem(onClick = onClick) {
        Text(text = stringResource(id = titleResId))
    }
}