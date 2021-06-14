package com.ang.acb.movienight.ui.filtermovies

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ang.acb.movienight.R
import com.ang.acb.movienight.data.asStringResourceId
import com.ang.acb.movienight.domain.MovieFilter

@Composable
fun MoviesTopBar(
    filterLabel: Int,
    onFilterChanged: (filter: MovieFilter) -> Unit
) {

    TopAppBar(
        title = {
            Text(text = stringResource(filterLabel))
        },
        actions = {
            MoviesMenu(onFilterChanged)
        }
    )
}

@Composable
fun MoviesMenu(
    onFilterChanged: (filter: MovieFilter) -> Unit,
) {
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
                    onFilterChanged(MovieFilter.POPULAR)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.TOP_RATED,
                onClick = {
                    onFilterChanged(MovieFilter.TOP_RATED)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.NOW_PLAYING,
                onClick = {
                    onFilterChanged(MovieFilter.NOW_PLAYING)
                    expanded = false
                },
            )

            FilterMenuItem(
                filter = MovieFilter.UPCOMING,
                onClick = {
                    onFilterChanged(MovieFilter.UPCOMING)
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
        Text(text = stringResource(id = filter.asStringResourceId()))
    }
}